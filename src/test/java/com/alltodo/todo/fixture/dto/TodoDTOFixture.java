package com.alltodo.todo.fixture.dto;

import com.alltodo.todo.dto.TodoDTO;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoDTOFixture {
    private static final UUID defaultTodoId = UUID.randomUUID();
    private static final String defaultTitle = "test";
    private static final AtomicInteger counter = new AtomicInteger(1);
    public static TodoDTO createDefaultTodoDTO() {
        return TodoDTO.builder()
                .todoId(defaultTodoId)
                .title(defaultTitle)
                .priority(counter.getAndIncrement())
                .build();
    }
}
