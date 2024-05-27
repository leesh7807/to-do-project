package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import lombok.AllArgsConstructor;

import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TodoMapper {
    private UserMapper userMapper;
    private TodoItemMapper todoItemMapper;
    public void toEntity(TodoDTO todoDTO) {

    }

    public void toDTO(Todo Todo) {

    }
}