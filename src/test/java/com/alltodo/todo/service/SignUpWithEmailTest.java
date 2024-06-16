package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
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
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = UserDTOFixture.createDefaultUserDTO();
    }

    @Test
    public void accepted() {
        assertDoesNotThrow(() -> userService.signUpWithEmail(userDTO));

        Optional<User> optionalUser = userRepository.findByEmailAndLoginMethod(userDTO.getEmail(), userDTO.getLoginMethod());

        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertAll(
                () -> assertEquals(userDTO.getEmail(), user.getEmail()),
                () -> assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getEncryptedPassword()))
        );
    }
}
