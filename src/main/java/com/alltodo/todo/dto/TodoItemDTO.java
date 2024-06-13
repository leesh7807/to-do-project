package com.alltodo.todo.dto;

import com.alltodo.todo.entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TodoItemDTO {
    @NotNull(message = "새로고침 후 다시 시도해 주세요.")
    private UUID itemId;
    private Status status;
    private LocalDateTime exp;
    @NotNull(message = "새로고침 후 다시 시도해 주세요.")
    @Positive(message = "새로고침 후 다시 시도해 주세요.")
    private Integer priority;
    private String content;
}
