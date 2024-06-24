package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignInWithEmailTest {
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    private UserService userService;

    @Test
    public void accepted() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        Authentication mockAuthentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuthentication);

        // when
        try {
            userService.signInWithEmail(userDTO);

        // then
        } catch(AuthenticationException ignored) {
            fail("Should Not Catch Exception");
        }
    }
}