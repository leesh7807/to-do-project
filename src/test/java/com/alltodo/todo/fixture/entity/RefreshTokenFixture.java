package com.alltodo.todo.fixture.entity;

import com.alltodo.todo.config.token_auth.RefreshToken;

import java.util.UUID;

public class RefreshTokenFixture {
    private static final String username = "test@naver.com";
    private static final String userAgent = "PC";
    private static final UUID token = UUID.randomUUID();

    public static RefreshToken createDefaultRefreshToken() {
        String key = username + ":" + userAgent;
        return RefreshToken.builder()
                .id(key)
                .refreshToken(token)
                .build();
    }
}
