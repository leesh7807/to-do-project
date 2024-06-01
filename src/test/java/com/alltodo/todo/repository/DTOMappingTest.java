package com.alltodo.todo.repository;

import com.alltodo.todo.configuration.SecurityConfig;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {SecurityConfig.class})
@SpringBootTest
public class DTOMappingTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test() {
        String email = "test@naver.com";
        String plainPassword = "qwer1234";
        LoginMethod loginMethod = LoginMethod.EMAIL;
        UserDTO userDTO = UserDTO.builder()
                .email(email)
                .plainPassword(plainPassword)
                .loginMethod(loginMethod)
                .build();
        User user = toEntity(UUID.randomUUID(), userDTO, new ArrayList<Todo>());
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals("test@naver.com", user.getEmail()),
                () -> assertTrue(passwordEncoder.matches(plainPassword, user.getPassword()))
        );
    }
    public User toEntity(UUID uuid, UserDTO userDTO, List<Todo> todos) {
        return User.builder()
                .userId(uuid)
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPlainPassword()))
                .loginMethod(userDTO.getLoginMethod())
                .todos(todos)
                .build();
    }
}
