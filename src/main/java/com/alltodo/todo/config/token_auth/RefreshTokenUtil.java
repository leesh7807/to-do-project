package com.alltodo.todo.config.token_auth;

import com.alltodo.todo.repository.RedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenUtil {
    private final RedisRepository redisRepository;

    public UUID generateUUIDRefreshToken() {
        return UUID.randomUUID();
    }

    public boolean validateRefreshToken(String username, String userAgent, UUID refreshToken) {
        String key  = username + ":" + userAgent;
        RefreshToken storedToken = redisRepository.findById(key).orElse(null);
        return storedToken != null && storedToken.getRefreshToken().equals(refreshToken);
    }

    @Transactional
    public void saveRefreshToken(String username, String userAgent, UUID refreshToken) {
        String key = username + ":" + userAgent;
        RefreshToken saveToken = RefreshToken.builder()
                .id(key)
                .refreshToken(refreshToken)
                .build();
        redisRepository.save(saveToken);
    }
}
