package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;
import com.alltodo.todo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoMapper {
    public Todo toEntity(TodoDTO todoDTO, User user, List<TodoItem> todoItems) {
        return Todo.builder()
                .todoId(todoDTO.getTodoId())
                .title(todoDTO.getTitle())
                .todoItems(todoItems)
                .user(user)
                .priority(todoDTO.getPriority())
                .build();
    }

    public TodoDTO toDTO(Todo todo) {
        return TodoDTO.builder()
                .todoId(todo.getTodoId())
                .title(todo.getTitle())
                .priority(todo.getPriority())
                .build();
    }
}