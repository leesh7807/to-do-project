package com.alltodo.todo.mapper;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private TodoMapper todoMapper;

    public void toEntity(UserDTO userDTO) {

    }

    public void toDTO(User user) {

    }
}
