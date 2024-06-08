package com.alltodo.todo.dto;

import com.alltodo.todo.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class TodoItemDTO {
    private UUID itemId;
    private Status status;
    private LocalDateTime exp;
    private Integer priority;
    private String content;
}
