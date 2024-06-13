package com.alltodo.todo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TodoDTO {
    @NotNull(message = "새로고침 후 다시 시도해 주세요.")
    private UUID todoId;
    private String title;
    @NotNull(message = "새로고침 후 다시 시도해 주세요.")
    @Positive(message = "새로고침 후 다시 시도해 주세요.")
    private Integer priority;
}
