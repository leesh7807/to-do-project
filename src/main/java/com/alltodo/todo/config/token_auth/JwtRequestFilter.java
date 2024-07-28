package com.alltodo.todo.config.token_auth;

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
import ua_parser.Parser;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final RefreshTokenUtil refreshTokenUtil;
    private final Parser parser;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String username = extractUsernameFromAccessToken(request);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                setAuthentication(request, userDetails);
            }
        } catch(ExpiredJwtException e) {
            handleExpiredToken(request, response, e);
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

    private void handleExpiredToken(HttpServletRequest request, HttpServletResponse response, ExpiredJwtException e) throws IOException {
        UUID refreshToken = extractRefreshToken(request);
        String expiredUsername = e.getClaims().getSubject();
        String userAgent = extractUserAgent(request);
        if(refreshTokenUtil.validateRefreshToken(expiredUsername, userAgent, refreshToken)) {
            // issue new refresh token
            UUID nextRefreshToken = refreshTokenUtil.generateUUIDRefreshToken();
            refreshTokenUtil.saveRefreshToken(expiredUsername, userAgent, nextRefreshToken);

            // issue new access token ************************

        }
        else {
            sendUnauthorizedResponse(response, "Token has expired");
        }
    }

    private UUID extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Refresh-Token"))
                .map(UUID::fromString)
                .orElse(null);
    }

    private String extractUserAgent(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("User-Agent"))
                .map(parser::parse)
                .map(client -> client.userAgent.family)
                .orElse("other");
    }

    private void handleInvalidToken(HttpServletResponse response) throws IOException{
        sendUnauthorizedResponse(response, "Error has occurred");
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
