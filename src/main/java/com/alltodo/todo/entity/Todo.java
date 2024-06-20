package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @UuidGenerator
    @Column(name = "todo_id")
    private UUID todoId;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<TodoItem> todoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "priority")
    private Integer priority;

    public void addItem(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    public void removeItem(TodoItem todoItem) {
        todoItems.remove(todoItem);
    }
}
