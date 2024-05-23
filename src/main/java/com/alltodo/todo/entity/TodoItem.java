package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo_items")
@Getter
@NoArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "exp")
    private LocalDateTime exp;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "content")
    private String content;

    @Builder
    public TodoItem(Todo todo, Status status, LocalDateTime exp, Integer priority, String content) {
        this.todo = todo;
        todo.getTodoItems().add(this);
        this.status = status;
        this.exp = exp;
        this.priority = priority;
        this.content = content;
    }
}
