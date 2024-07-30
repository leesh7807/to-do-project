package com.alltodo.todo.controller;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid UserDTO userDTO) {
        try {
            userService.join(userDTO);
        } catch(UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error : User already exists.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User joined successfully.");
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid UserDTO userDTO) {
        String accessToken = userService.login(userDTO);
    }
}
