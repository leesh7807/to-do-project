package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
    @Transactional
    public void signUpWithEmail(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        LoginMethod loginMethod = userDTO.getLoginMethod();

        if(loginMethod != LoginMethod.EMAIL) {
            throw new IllegalArgumentException(loginMethod.toString());
        }

        Optional<User> duplicatedEmailUser = userRepository.findByEmail(email);
        if (duplicatedEmailUser.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }

        User newUser = User.builder().
                email(email).
                encryptedPassword(passwordEncoder.encode(password)).
                loginMethod(loginMethod)
                .build();
        userRepository.save(newUser);
    }
}
