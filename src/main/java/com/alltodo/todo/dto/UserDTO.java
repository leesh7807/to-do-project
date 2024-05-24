package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {
    private String email;
    private String password;
    private LoginMethod loginMethod;
    private List<TodoDTO> todoDTOs;

    @Builder
    public UserDTO(String email, String password, LoginMethod loginMethod, List<TodoDTO> todoDTOs) {
        this.email = email;
        this.password = password;
        this.loginMethod = loginMethod;
        this.todoDTOs = todoDTOs;
    }

}
