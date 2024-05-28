package com.alltodo.todo.dto;

import com.alltodo.todo.entity.LoginMethod;
import lombok.*;

import java.util.List;

@Getter
public class UserDTO {
    private String email;
    private String plainPassword;
    private LoginMethod loginMethod;
}
