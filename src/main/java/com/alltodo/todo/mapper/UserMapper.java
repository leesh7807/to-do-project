package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .loginMethod(user.getLoginMethod())
                .todoDTOs(user.getTodos().stream().map(TodoMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
