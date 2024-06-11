package com.alltodo.todo;

import com.alltodo.todo.configuration.SecurityConfig;
import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.dto.TodoItemDTO;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.fixture.dto.TodoDTOFixture;
import com.alltodo.todo.fixture.dto.TodoItemDTOFixture;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.fixture.entity.TodoFixture;
import com.alltodo.todo.fixture.entity.TodoItemFixture;
import com.alltodo.todo.fixture.entity.UserFixture;
import com.alltodo.todo.mapper.TodoItemMapper;
import com.alltodo.todo.mapper.TodoMapper;
import com.alltodo.todo.mapper.UserMapper;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {SecurityConfig.class, UserMapper.class, TodoMapper.class, TodoItemMapper.class})
@SpringBootTest
public class EntityDTOMappingTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TodoMapper todoMapper;
    @Autowired
    private TodoItemMapper todoItemMapper;
    @Test
    @DisplayName("UserDTO to User Entity")
    public void DTOToUserTest() {
        // given
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        List<Todo> todos = new ArrayList<>();

        // when
        User user = userMapper.toEntity(userId, userDTO, todos);

        //then
        assertAll(
                () -> assertNotNull(user),
                () -> assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getEncryptedPassword()))
        );
    }

    @Test
    @DisplayName("User Entity to UserDTO")
    public void userToDTOTest() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        UserDTO userDTO = userMapper.toDTO(user);

        // then
        assertAll(
                () -> assertNotNull(userDTO),
                () -> assertEquals(user.getEncryptedPassword(), userDTO.getPassword())
        );
    }

    @Test
    @DisplayName("TodoDTO to Todo Entity")
    public void DTOToTodoTest() {
        // given
        TodoDTO todoDTO = TodoDTOFixture.createDefaultTodoDTO();
        User user = UserFixture.createDefaultUser();
        List<TodoItem> todoItems = new ArrayList<>();

        // when
        Todo todo = todoMapper.toEntity(todoDTO, user, todoItems);

        // then
        assertAll(
                () -> assertNotNull(todo),
                () -> assertEquals(todoDTO.getTodoId(), todo.getTodoId())
        );
    }

    @Test
    @DisplayName("Todo Entity to TodoDTO")
    public void TodoToDTOTest() {
        // given
        Todo todo = TodoFixture.createDefaultTodo();

        // when
        TodoDTO todoDTO = todoMapper.toDTO(todo);

        // then
        assertAll(
                () -> assertNotNull(todoDTO),
                () -> assertEquals(todo.getTodoId(), todoDTO.getTodoId())
        );
    }

    @Test
    @DisplayName("TodoItemDTO to TodoItem Entity")
    public void DTOToTodoItemTest() {
        // given
        TodoItemDTO todoItemDTO = TodoItemDTOFixture.createDefaultTodoItemDTO();
        Todo todo = TodoFixture.createDefaultTodo();

        // when
        TodoItem todoItem = todoItemMapper.toEntity(todoItemDTO, todo);

        // then
        assertAll(
                () -> assertNotNull(todoItem),
                () -> assertEquals(todoItemDTO.getItemId(), todoItem.getItemId())
        );
    }

    @Test
    @DisplayName("TodoItem Entity to TodoItemDTO")
    public void TodoItemToDTOTest() {
        // given
        TodoItem todoItem = TodoItemFixture.createDefaultTodoItem();

        // when
        TodoItemDTO todoItemDTO = todoItemMapper.toDTO(todoItem);

        // then
        assertAll(
                () -> assertNotNull(todoItemDTO),
                () -> assertEquals(todoItem.getItemId(), todoItemDTO.getItemId())
        );
    }
}
