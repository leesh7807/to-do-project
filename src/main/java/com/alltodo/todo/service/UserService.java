package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signUpWithEmail(UserDTO userDTO) throws IllegalArgumentException, UserAlreadyExistsException {
        validateLoginMethod(LoginMethod.EMAIL, userDTO.getLoginMethod());
        validateThereAreNoDuplicateEmail(userDTO.getEmail());

        User newUser = User.builder().
                email(userDTO.getEmail()).
                encryptedPassword(passwordEncoder.encode(userDTO.getPassword())).
                loginMethod(userDTO.getLoginMethod())
                .build();
        userRepository.save(newUser);
    }

    @Transactional
    public void signInWithEmail(UserDTO userDTO) throws IllegalArgumentException, AuthenticationException {
        validateLoginMethod(LoginMethod.EMAIL, userDTO.getLoginMethod());

        // spring-security authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // return jwt token
    }

    public void validateLoginMethod(LoginMethod expected, LoginMethod actual) {
        if(expected != actual) throw new IllegalArgumentException(actual.toString());
    }

    public void validateThereAreNoDuplicateEmail(String email) {
        Optional<User> duplicateEmailUser = userRepository.findByEmail(email);
        if (duplicateEmailUser.isPresent()) throw new UserAlreadyExistsException(email);
    }
}
