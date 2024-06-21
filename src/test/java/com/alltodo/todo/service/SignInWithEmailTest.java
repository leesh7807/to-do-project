package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class SignInWithEmailTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    public void test() {
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        LoginMethod loginMethod = userDTO.getLoginMethod();

        Optional<User> registeredUser = userRepository.findByEmail(userDTO.getEmail());

        if(registeredUser.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        if(loginMethod != LoginMethod.EMAIL) {
            throw new IllegalArgumentException(loginMethod.toString());
        }


    }
}