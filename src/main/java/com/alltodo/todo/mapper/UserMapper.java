package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .loginMethod(user.getLoginMethod())
                .todoDTOs(user.getTodos().stream().map(TodoMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public User toEntity(UUID userId, UserDTO userDTO) {
        return User.builder()
                .userId(userId)
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .loginMethod(userDTO.getLoginMethod())
                .todos(userDTO.getTodoDTOs().stream().map(TodoMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
