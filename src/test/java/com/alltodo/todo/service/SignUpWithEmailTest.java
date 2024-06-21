package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.fixture.entity.UserFixture;
import com.alltodo.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpWithEmailTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void accepted() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        String encryptedPassword = "encodedTest123!!";
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn(encryptedPassword);

        // when
        try {
            userService.signUpWithEmail(userDTO);

        // then
        } catch(Exception e) {
            fail("There is Exception: " + e.getMessage());
        }
    }

    @Test
    public void whenNotEmailMethod() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        userDTO.setLoginMethod(LoginMethod.OAUTH);

        // when
        try {
            userService.signUpWithEmail(userDTO);

        // then
            fail("should throw IllegalArgumentException");
        } catch(IllegalArgumentException ignored) {

        }
    }

    @Test
    public void whenUserAlreadyExists() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        User user = UserFixture.createDefaultUser();
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        // when
        try {
            userService.signUpWithEmail(userDTO);

        // then
            fail("should throw UserAlreadyExistsException");
        } catch(UserAlreadyExistsException ignored) {

        }
    }
}
