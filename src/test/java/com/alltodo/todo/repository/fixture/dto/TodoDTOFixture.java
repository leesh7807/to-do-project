package com.alltodo.todo.repository.fixture.dto;

import com.alltodo.todo.dto.TodoDTO;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoDTOFixture {
    private static final AtomicInteger counter = new AtomicInteger();
    public static TodoDTO createDefaultTodoDTO() {
        return TodoDTO.builder()
                .todoId(UUID.randomUUID())
                .title("test")
                .priority(counter.getAndIncrement())
                .build();
    }
}
