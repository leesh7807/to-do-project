package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class SignInWithEmailTest {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    UserService userService;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        // sign up
        userDTO = UserDTOFixture.createDefaultUserDTO();
        userService.signUpWithEmail(userDTO);
    }

    @Test
    public void whenUserSignInSuccessfully() {
        assertAll(
                // sign in
                () -> assertDoesNotThrow(() -> {
                Authentication auth = authenticationManagerBuilder.getObject().authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(auth);}),
                () -> assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
                );
    }
}