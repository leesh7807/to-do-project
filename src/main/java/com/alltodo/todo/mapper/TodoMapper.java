package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.entity.Todo;

public class TodoMapper {
    public static TodoDTO toDTO(Todo todo) {
        return new TodoDTO();
    }
}
