package com.alltodo.todo.fixture.entity;

import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;
import com.alltodo.todo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoFixture {
    private static  final UUID defaultTodoId = UUID.randomUUID();
    private static final String defaultTitle = "test";
    private static final List<TodoItem> defaultTodoItems = new ArrayList<>();
    private static final User defaultUser = UserFixture.createDefaultUser();
    private static final AtomicInteger counter = new AtomicInteger();
    public static Todo createDefaultTodo() {
        return Todo.builder()
                .todoId(defaultTodoId)
                .title(defaultTitle)
                .todoItems(defaultTodoItems)
                .user(defaultUser)
                .priority(counter.getAndIncrement())
                .build();
    }
}
