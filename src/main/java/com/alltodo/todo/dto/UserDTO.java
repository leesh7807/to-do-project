package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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

    @Pattern.List({
            @Pattern(regexp = ".*[a-z|A-Z].*", message = "패스워드는 하나 이상의 영문자를 포함해야 합니다."),
            @Pattern(regexp = ".*\\d.*", message = "패스워드는 하나 이상의 숫자를 포함해야 합니다."),
            @Pattern(regexp = ".*[!@#$%^&*()_+?].*", message = "패스워드는 하나 이상의 특수문자를 포함해야 합니다."),
            @Pattern(regexp = "\\S+", message = "패스워드는 공백을 포함할 수 없습니다."),
            @Pattern(regexp = ".{8,20}", message = "패스워드의 길이는 8자 이상 20자 이하입니다.")
    })
    private String password;
    private LoginMethod loginMethod;
}
