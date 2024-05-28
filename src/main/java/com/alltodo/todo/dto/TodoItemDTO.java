package com.alltodo.todo.dto;

import com.alltodo.todo.entity.Status;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoItemDTO {
    private Long itemId;
    private Status status;
    private LocalDateTime exp;
    private Integer priority;
    private String content;
}
