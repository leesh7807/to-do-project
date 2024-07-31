package com.alltodo.todo.service;

        import com.alltodo.todo.config.token_auth.JwtUtil;
        import com.alltodo.todo.config.token_auth.RefreshTokenUtil;
        import com.alltodo.todo.dto.AuthTokenDTO;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;

        import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final RefreshTokenUtil refreshTokenUtil;
    public AuthTokenDTO makeAuthTokenDTO(String username, String userAgent) {
        String accessToken = jwtUtil.generateAccessToken(username);

        UUID refreshToken = refreshTokenUtil.generateUUIDRefreshToken();
        String refreshTokenKey = refreshTokenUtil.makeRefreshTokenKey(username, userAgent);
        refreshTokenUtil.saveRefreshToken(refreshTokenKey, refreshToken);

        return new AuthTokenDTO(accessToken, refreshToken);
    }
}
