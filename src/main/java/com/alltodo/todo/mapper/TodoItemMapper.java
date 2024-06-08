package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoItemDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;
import org.springframework.stereotype.Component;

@Component
public class TodoItemMapper {
    public TodoItem toEntity(TodoItemDTO todoItemDTO, Todo todo) {
        return TodoItem.builder()
                .itemId(todoItemDTO.getItemId())
                .todo(todo)
                .status(todoItemDTO.getStatus())
                .exp(todoItemDTO.getExp())
                .priority(todoItemDTO.getPriority())
                .content(todoItemDTO.getContent())
                .build();
    }

    public TodoItemDTO toDTO(TodoItem todoItem) {
        return TodoItemDTO.builder()
                .itemId(todoItem.getItemId())
                .status(todoItem.getStatus())
                .exp(todoItem.getExp())
                .priority(todoItem.getPriority())
                .content(todoItem.getContent())
                .build();
    }
}
