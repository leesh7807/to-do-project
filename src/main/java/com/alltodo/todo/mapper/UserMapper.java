package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public User toEntity(UUID uuid, UserDTO userDTO, List<Todo> todos) {
        return User.builder()
                .userId(uuid)
                .email(userDTO.getEmail())
                .encryptedPassword(passwordEncoder.encode(userDTO.getPassword()))
                .loginMethod(userDTO.getLoginMethod())
                .todos(todos)
                .build();
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getEncryptedPassword())
                .loginMethod(user.getLoginMethod())
                .build();
    }
}
