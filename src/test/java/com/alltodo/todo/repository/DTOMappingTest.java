package com.alltodo.todo.repository;

import com.alltodo.todo.configuration.SecurityConfig;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SecurityConfig.class})
@SpringBootTest
public class DTOMappingTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(UserDTO userDTO) {
        String Email = userDTO.getEmail();
        String password = userDTO.getPlainPassword();
        return User.builder().build();
    }
}
