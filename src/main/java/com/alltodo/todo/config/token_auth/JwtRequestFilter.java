package com.alltodo.todo.config.token_auth;

import com.alltodo.todo.dto.AuthTokenDTO;
import com.alltodo.todo.service.AuthService;
import com.alltodo.todo.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenUtil refreshTokenUtil;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String username = extractUsernameFromAccessToken(request);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                setAuthentication(request, userDetails);
            }
        /* If there are no token, 'Exception' does not lead to 'Return'.
         * To handle all exceptions consistently rather than locally, you should implement a custom 'AuthenticationEntryPoint'.
         * 'ExceptionTranslatorFilter' in Spring-Security routes all exceptions in security filter chain to the 'AuthenticationEntryPoint' and 'AccessDeniedException'.
         */
        } catch(ExpiredJwtException e) {
            processExpiredToken(request, response, e);
            return;
        } catch(JwtException e) {
            handleInvalidToken(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String extractUsernameFromAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(it -> it.startsWith("Bearer "))
                .map(it -> it.substring(7))
                .map(jwtUtil::parseToken)
                .map(Claims::getSubject)
                .orElse(null);
    }

    private void processExpiredToken(HttpServletRequest request, HttpServletResponse response, ExpiredJwtException e) throws IOException {
        UUID refreshToken = extractRefreshToken(request);
        if(refreshToken == null) {
            sendUnauthorizedResponse(response, "Token expired");
            return;
        }

        String username = e.getClaims().getSubject();
        String userAgent = request.getHeader("user-agent");
        String refreshTokenKey = refreshTokenUtil.makeRefreshTokenKey(username, userAgent);
        if(refreshTokenUtil.validateRefreshToken(refreshTokenKey, refreshToken)) {
            AuthTokenDTO authToken = authService.makeAuthTokenDTO(username, userAgent);

            response.setHeader("Authorization", authToken.getAccessTokenWithBearer());
            response.setHeader("Refresh-Token", authToken.getRefreshTokenAtString());

            sendUnauthorizedResponse(response, "New tokens issued");
        }
        else {
            // to-do: add refresh token delete method
            refreshTokenUtil.deleteRefreshTokenById(refreshTokenKey);

            sendUnauthorizedResponse(response, "Error occurred. Please re-login");
        }
    }

    private UUID extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Refresh-Token"))
                .map(UUID::fromString)
                .orElse(null);
    }

    private void handleInvalidToken(HttpServletResponse response) throws IOException{
        sendUnauthorizedResponse(response, "Error occurred. Please re-login");
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"message\": \"" + message + "\"}");
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
