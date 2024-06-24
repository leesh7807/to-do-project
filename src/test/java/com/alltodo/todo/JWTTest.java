package com.alltodo.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTTest {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Test
    public void test() {
        System.out.println(jwtSecret);
    }
}
