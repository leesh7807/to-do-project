package com.alltodo.todo.repository.mapping;

import com.alltodo.todo.configuration.SecurityConfig;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {SecurityConfig.class})
@SpringBootTest
public class UserMappingTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    @DisplayName("UserDTO to User Entity")
    public void userToEntityTest() {
        // given
        String email = "test@naver.com";
        String password = "qwer1234";
        LoginMethod loginMethod = LoginMethod.EMAIL;
        UserDTO userDTO = UserDTO.builder()
                .email(email)
                .password(password)
                .loginMethod(loginMethod)
                .build();

        // when
        User user = toEntity(UUID.randomUUID(), userDTO, new ArrayList<Todo>());

        //then
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals("test@naver.com", user.getEmail()),
                () -> assertTrue(passwordEncoder.matches(password, user.getEncryptedPassword()))
        );
    }

    @Test
    @DisplayName("User Entity to UserDTO")
    public void userDTOToEntityTest() {
        // given
        UUID uuid = UUID.randomUUID();
        String email = "test@naver.com";
        String password = passwordEncoder.encode("qwer1234");
        LoginMethod loginMethod = LoginMethod.EMAIL;
        List<Todo> todos = new ArrayList<>();
        User user = User.builder()
                .userId(uuid)
                .email(email)
                .encryptedPassword(password)
                .loginMethod(loginMethod)
                .todos(todos)
                .build();

        // when
        UserDTO userDTO = toDTO(user);

        // then
        assertAll(
                () -> assertNotNull(userDTO),
                () -> assertEquals(userDTO.getEmail(), email),
                () -> assertEquals(userDTO.getPassword(), password),
                () -> assertEquals(userDTO.getLoginMethod(), loginMethod)
        );
    }
    public User toEntity(UUID uuid, UserDTO userDTO, List<Todo> todos) {
        return User.builder()
                .userId(uuid)
                .email(userDTO.getEmail())
                .encryptedPassword(passwordEncoder.encode(userDTO.getPassword()))
                .loginMethod(userDTO.getLoginMethod())
                .todos(todos)
                .build();
    }

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getEncryptedPassword())
                .loginMethod(user.getLoginMethod())
                .build();
    }
}
