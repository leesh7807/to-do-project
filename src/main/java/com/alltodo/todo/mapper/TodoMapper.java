package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoDTO toDTO(Todo todo) {
        return TodoDTO.builder()
                .todoId(todo.getTodoId())
                .title(todo.getTitle())
                .priority(todo.getPriority())
                .build();
    }
}