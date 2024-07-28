package com.alltodo.todo.config.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@ConfigurationProperties(prefix = "spring.data.redis")
@EnableRedisRepositories
@RequiredArgsConstructor
@Getter
public class RedisConfiguration {
    private final String host;
    private final int port;
}
