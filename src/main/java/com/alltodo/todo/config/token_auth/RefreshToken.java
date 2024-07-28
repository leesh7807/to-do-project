package com.alltodo.todo.config.token_auth;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@RedisHash(value = "refresh", timeToLive = 1209600)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    private String id;

    private UUID refreshToken;
}
