package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.entity.Todo;

public class TodoMapper {
    public static TodoDTO toDTO(Todo todo) {
        // have to implement toDTO func
        return new TodoDTO();
    }

    public static Todo toEntity(TodoDTO todoDTO) {
        // have to implement toEntity func
        return Todo.builder().build();
    }
}