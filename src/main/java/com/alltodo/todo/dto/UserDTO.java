package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserDTO {
    private String email;
    private String password;
    private LoginMethod loginMethod;
}
