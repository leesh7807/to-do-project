package com.alltodo.todo.service;

import com.alltodo.todo.config.token_auth.JwtUtil;
import com.alltodo.todo.dto.UserDTO;
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
    private final JwtUtil jwtUtil;

    @Transactional
    public void join(UserDTO userDTO) throws UserAlreadyExistsException {
        validateThereAreNoDuplicateEmail(userDTO.getEmail());

        User newUser = User.builder().
                email(userDTO.getEmail()).
                encryptedPassword(passwordEncoder.encode(userDTO.getPassword())).
                loginMethod(userDTO.getLoginMethod())
                .build();
        userRepository.save(newUser);
    }

    @Transactional
    public String login(UserDTO userDTO) throws AuthenticationException {
        // spring-security authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // return jwt token
        return jwtUtil.generateAccessToken(userDTO.getEmail());
    }

    public void validateThereAreNoDuplicateEmail(String email) {
        Optional<User> duplicateEmailUser = userRepository.findByEmail(email);
        if (duplicateEmailUser.isPresent()) throw new UserAlreadyExistsException(email);
    }
}
