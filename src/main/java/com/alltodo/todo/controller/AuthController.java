package com.alltodo.todo.controller;

import com.alltodo.todo.dto.AuthTokenDTO;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.exception.UserAlreadyExistsException;
import com.alltodo.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        return ResponseEntity.status(HttpStatus.OK)
                .body("User join successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO userDTO, @RequestHeader("user-agent") String userAgent) {
        try {
            AuthTokenDTO authToken = userService.login(userDTO, userAgent);

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Authorization", authToken.getAccessTokenWithBearer())
                    .header("Refresh-Token", authToken.getRefreshTokenAtString())
                    .body("User login successfully.");
        } catch(AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Your email or password is incorrect.");
        }
    }
}
