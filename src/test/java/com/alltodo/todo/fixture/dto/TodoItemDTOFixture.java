package com.alltodo.todo.fixture.dto;

import com.alltodo.todo.dto.TodoItemDTO;
import com.alltodo.todo.entity.Status;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoItemDTOFixture {
    private static final UUID defaultItemId = UUID.randomUUID();
    private static final Status defaultStatus = Status.Pending;
    private static final LocalDateTime defaultExp = LocalDateTime.now();
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final String defaultContent = "test";
    public static TodoItemDTO createDefaultTodoItemDTO() {
        return TodoItemDTO.builder()
                .itemId(defaultItemId)
                .status(defaultStatus)
                .exp(defaultExp)
                .priority(counter.getAndIncrement())
                .content(defaultContent)
                .build();
    }
}
