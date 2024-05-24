package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long todoId;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    List<TodoItem> todoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "owner")
    private String owner;

    @Column(name = "priority")
    private Integer priority;
    @Builder
    public Todo(User user, String owner, Integer priority) {
        this.todoItems = new ArrayList<>();
        this.user = user;
        user.getTodos().add(this);
        this.owner = owner;
        this.priority = priority;
    }
}
