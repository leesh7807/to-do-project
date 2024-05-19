package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "todo_items")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoItem {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Column(name = "exp")
    private Date exp;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "content")
    private String content;
}
