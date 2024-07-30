package com.alltodo.todo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class AuthTokenDTO {
    private final String accessToken;
    private final UUID refreshToken;
}
