package com.alltodo.todo.config.token_auth;

import com.alltodo.todo.repository.RedisRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua_parser.Parser;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenUtil {
    private final RedisRepository redisRepository;
    private final Parser parser;

    public UUID generateUUIDRefreshToken() {
        return UUID.randomUUID();
    }

    public boolean validateRefreshToken(String key, UUID refreshToken) {
        RefreshToken storedToken = redisRepository.findById(key).orElse(null);
        return storedToken != null && storedToken.getRefreshToken().equals(refreshToken);
    }

    public String makeRefreshTokenKey(String username, String userAgent) {
        String essenceUserAgent = extractEssenceUserAgent(userAgent);
        return username + ":" + essenceUserAgent;
    }

    private String extractEssenceUserAgent(String userAgent) {
        return Optional.ofNullable(userAgent)
                .map(parser::parse)
                .map(client -> client.device.family + client.userAgent.family)
                .orElse("other");
    }

    @Transactional
    public void saveRefreshToken(String key, UUID refreshToken) {
        RefreshToken saveToken = RefreshToken.builder()
                .id(key)
                .refreshToken(refreshToken)
                .build();
        redisRepository.save(saveToken);
    }

    @Transactional
    public void deleteRefreshTokenById(String key) {
        redisRepository.deleteById(key);
    }
}
