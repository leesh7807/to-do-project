package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "todo_id")
    private Long todoId;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TodoItem> todoItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "owner")
    private String owner;

    @Column(name = "priority")
    private Integer priority;
}
