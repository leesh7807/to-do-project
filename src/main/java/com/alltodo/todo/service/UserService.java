package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.exception.InvalidLoginMethodException;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUpWithEmail(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        LoginMethod loginMethod = userDTO.getLoginMethod();

        if(loginMethod != LoginMethod.EMAIL) {
            throw new InvalidLoginMethodException("Invalid Login Method");
        }

        Optional<User> optionalUser = userRepository.findByEmailAndLoginMethod(email, loginMethod);
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email" + email + "Already Exists");
        }

        User newUser = User.builder().
                email(email).
                encryptedPassword(passwordEncoder.encode(password)).
                loginMethod(loginMethod)
                .build();
        userRepository.save(newUser);
    }
}
