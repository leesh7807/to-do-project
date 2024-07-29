package com.alltodo.todo.service;

import com.alltodo.todo.config.token_auth.RefreshToken;
import com.alltodo.todo.config.token_auth.RefreshTokenUtil;
import com.alltodo.todo.fixture.entity.RefreshTokenFixture;
import com.alltodo.todo.repository.RedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RefreshTokenUtilTest {
    @Autowired
    RedisRepository redisRepository;
    @Autowired
    RefreshTokenUtil refreshTokenUtil;

    @Test
    public void accepted() {
        // default: test@naver.com, PC, UUID
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        redisRepository.save(refreshToken);

        assertTrue(refreshTokenUtil.validateRefreshToken("test@naver.com", "PC", refreshToken.getRefreshToken()));
    }

    @Test
    public void UUIDMiss() {
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        redisRepository.save(refreshToken);

        assertFalse(refreshTokenUtil.validateRefreshToken("test@naver.com", "PC", UUID.randomUUID()));
    }
}
