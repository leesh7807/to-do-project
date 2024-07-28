package com.alltodo.todo.fixture.entity;

import com.alltodo.todo.entity.RefreshToken;

import java.util.UUID;

public class RefreshTokenFixture {
    private static final String username = "test@naver.com";
    private static final String deviceType = "PC";
    private static final UUID token = UUID.randomUUID();

    public static RefreshToken createDefaultRefreshToken() {
        String key = username + ":" + deviceType;
        return RefreshToken.builder()
                .id(key)
                .refreshToken(token)
                .build();
    }
}
