package com.alltodo.todo.dto;

import java.util.UUID;

public record AuthTokenDTO(String accessToken, UUID refreshToken) {
    public String getAccessTokenWithBearer() {
        return "Bearer " + accessToken;
    }

    public String getRefreshTokenAtString() {
        return refreshToken.toString();
    }
}
