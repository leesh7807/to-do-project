package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.TodoItemDTO;
import com.alltodo.todo.entity.TodoItem;
import org.springframework.stereotype.Component;

@Component
public class TodoItemMapper {
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
