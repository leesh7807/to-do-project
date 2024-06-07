package com.alltodo.todo.repository.fixture.entity;

import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoFixture {
    private static final User defaultUser = UserFixture.createDefaultUser();
    private static final AtomicInteger counter = new AtomicInteger();
    public static Todo createDefaultTodo() {
        return Todo.builder()
                .todoId(UUID.randomUUID())
                .title("test")
                .todoItems(new ArrayList<>())
                .user(defaultUser)
                .priority(counter.getAndIncrement())
                .build();
    }
}
