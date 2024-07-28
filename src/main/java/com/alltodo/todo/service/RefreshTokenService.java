package com.alltodo.todo.service;

import com.alltodo.todo.entity.RefreshToken;
import com.alltodo.todo.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisRepository redisRepository;

    public RefreshToken saveRefreshToken(String username, String deviceType, UUID refreshToken) {
        String key = username + ":" + deviceType;
        RefreshToken saveToken = RefreshToken.builder()
                .id(key)
                .refreshToken(refreshToken)
                .build();
        return redisRepository.save(saveToken);
    }
    public UUID generateUUIDRefreshToken() {
        return UUID.randomUUID();
    }

    public boolean validateRefreshToken(String username, String deviceType, UUID refreshToken) {
        String key  = username + ":" + deviceType;
        RefreshToken storedToken = redisRepository.findById(key).orElse(null);
        return storedToken != null && storedToken.getRefreshToken().equals(refreshToken);
    }

    public void deleteRefreshToken(String username, String deviceType) {
        String key = username + ":" + deviceType;
        redisRepository.deleteById(key);
    }
}
