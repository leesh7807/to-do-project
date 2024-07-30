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

    @Test
    public void findRefreshToken() {
        // given
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        redisRepository.save(refreshToken);

        // when
        RefreshToken findingRefreshToken = redisRepository.findById(refreshToken.getId()).orElse(null);

        // then
        assertNotNull(findingRefreshToken);
        assertAll(
                () -> assertEquals(findingRefreshToken.getId(), refreshToken.getId()),
                () -> assertEquals(findingRefreshToken.getRefreshToken().toString(),
                        refreshToken.getRefreshToken().toString())
        );
    }

    @Test
    public void deleteRefreshToken() {
        // given
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        redisRepository.save(refreshToken);

        // when
        redisRepository.deleteById(refreshToken.getId());
        RefreshToken deletedRefreshToken = redisRepository.findById(refreshToken.getId()).orElse(null);

        // then
        assertNull(deletedRefreshToken);
    }
}
