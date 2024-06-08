package com.alltodo.todo.fixture.entity;

import com.alltodo.todo.entity.Status;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoItemFixture {
    private static final UUID defaultItemId = UUID.randomUUID();
    private static final Todo defaultTodo = TodoFixture.createDefaultTodo();
    private static final Status defaultStatus = Status.Pending;
    private static final LocalDateTime defaultExp = LocalDateTime.now();
    private static final AtomicInteger counter = new AtomicInteger();
    private static final String defaultContent = "test";
    public static TodoItem createDefaultTodoItem() {
        return TodoItem.builder()
                .itemId(defaultItemId)
                .todo(defaultTodo)
                .status(defaultStatus)
                .exp(defaultExp)
                .priority(counter.getAndIncrement())
                .content(defaultContent)
                .build();
    }
}
