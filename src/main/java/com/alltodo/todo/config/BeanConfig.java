package com.alltodo.todo.config;

import com.alltodo.todo.config.configuration.RedisConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import ua_parser.Parser;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {
    private final RedisConfiguration redisConfiguration;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisConfiguration.getHost(), redisConfiguration.getPort());
    }

    @Bean
    public Parser parser() {
        return new Parser();
    }
}
