package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SignUpWithEmailTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = UserDTOFixture.createDefaultUserDTO();
    }

    @Test
    public void accepted() {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        LoginMethod loginMethod = userDTO.getLoginMethod();

        User testUser;
        if(loginMethod == LoginMethod.EMAIL) {
            Optional<User> optionalUser = userRepository.findByEmailAndLoginMethod(email, loginMethod);
            if(optionalUser.isEmpty()) {
                User newUser = User.builder().
                        email(email).
                        encryptedPassword(passwordEncoder.encode(password)).
                        loginMethod(loginMethod)
                        .build();

                testUser = userRepository.save(newUser);
            } else {
                testUser = null;
            }
        } else {
            testUser = null;
        }

        assertNotNull(testUser);
        assertAll("User properties",
                () -> assertEquals(email, testUser.getEmail()),
                () -> assertTrue(passwordEncoder.matches(password, testUser.getEncryptedPassword()))
        );
    }
}
