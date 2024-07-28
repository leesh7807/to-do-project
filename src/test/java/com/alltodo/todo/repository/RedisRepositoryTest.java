package com.alltodo.todo.repository;

import com.alltodo.todo.config.token_auth.RefreshToken;
import com.alltodo.todo.fixture.entity.RefreshTokenFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisRepositoryTest {
    @Autowired
    RedisRepository redisRepository;

    @Test
    public void saveRefreshToken() {
        // given
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();

        // when
        RefreshToken savedRefreshToken = redisRepository.save(refreshToken);

        // then
        assertNotNull(savedRefreshToken);
        assertAll(
                () -> assertEquals(refreshToken.getId(), savedRefreshToken.getId()),
                () -> assertEquals(refreshToken.getRefreshToken(), savedRefreshToken.getRefreshToken())
        );
    }
}
