package com.alltodo.todo.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserTodoId implements Serializable {
    private UUID user_id;
    private Long todo_id;
}
