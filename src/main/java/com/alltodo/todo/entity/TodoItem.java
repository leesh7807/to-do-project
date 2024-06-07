package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "todo_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TodoItem {
    @Id
    @UuidGenerator
    @Column(name = "item_id")
    private UUID itemId;

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
}
