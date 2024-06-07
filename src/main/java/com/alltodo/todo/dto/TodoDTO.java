package com.alltodo.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class TodoDTO {
    private UUID todoId;
    private String title;
    private Integer priority;
}
