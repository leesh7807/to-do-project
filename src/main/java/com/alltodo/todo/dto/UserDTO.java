package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
public class UserDTO {
    private String email;
    private String password;
    private LoginMethod loginMethod;
}
