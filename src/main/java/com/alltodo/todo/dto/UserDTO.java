package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDTO {
    @Email(regexp = "^[a-zA-Z0-9-_]+@[a-zA-Z0-9.-_]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이어야 합니다.")
    @Size(max = 255, message = "이메일 최대 길이는 255자를 넘을 수 없습니다.")
    private String email;

    private String password;
    private LoginMethod loginMethod;
}
