package com.alltodo.todo.fixture.entity;

import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserFixture {
    private static final UUID defaultUserId = UUID.randomUUID();
    private static final String defaultEmail = "test@naver.com";
    private static final String defaultEncryptedPassword = "$2a$10$2GKshOTKJLpDvRz9zhcBde7lqKYcOTn7pf9kJHp11HJnlQQv6i8l6";
    private static final LoginMethod defaultLoginMethod = LoginMethod.EMAIL;
    private static final List<Todo> defaultTodos = new ArrayList<>();
    public static User createDefaultUser() {
        return User.builder()
                .userId(defaultUserId)
                .email(defaultEmail)
                .encryptedPassword(defaultEncryptedPassword)
                .loginMethod(defaultLoginMethod)
                .todos(defaultTodos)
                .build();
    }
}
