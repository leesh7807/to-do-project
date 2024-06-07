package com.alltodo.todo.repository.fixture.entity;

import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.UUID;

public class UserFixture {
    public static User createDefaultUser() {
        return User.builder()
                .userId(UUID.randomUUID())
                .email("test@naver.com")
                .encryptedPassword("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .todos(new ArrayList<>())
                .build();
    }
}
