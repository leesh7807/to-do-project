package com.alltodo.todo.repository;

import com.alltodo.todo.entity.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 인메모리 데이터베이스로 대체하지 않음
public class RepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoItemRepository todoItemRepository;

    private User user1;
    @BeforeEach
    @Transactional
    public void setUp() {
        // Given
        user1 = User.builder()
                .email("leesh7807@gmail.com")
                .password("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .build();
    }
    @Test
    @DisplayName("user 저장")
    public void saveAndFindUser() {
        // When
        User savedUser1 = userRepository.save(user1);
        Optional<User> retrievedUser = userRepository.findById(savedUser1.getUserId());


        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo("leesh7807@gmail.com");
    }

    @Test
    @DisplayName("user에 todo 저장")
    public void getUsersTodos() {
        // Given
        User savedUser1 = userRepository.save(user1);
        Todo todo1 = Todo.builder()
                .user(savedUser1)
                .owner("leesh7807@gmail.com")
                .priority(0)
                .build();
        Todo todo2 = Todo.builder()
                .user(savedUser1)
                .owner("leesh7807@naver.com")
                .priority(1)
                .build();

        // When
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        Optional<User> retrievedUser = userRepository.findById(savedUser1.getUserId());

        // Then
        assertThat(retrievedUser).isPresent();
        List<Todo> todos = retrievedUser.get().getTodos();
        assertThat(todos.get(0).getOwner()).isEqualTo("leesh7807@gmail.com");
        assertThat(todos.get(1).getOwner()).isEqualTo("leesh7807@naver.com");
    }

    @Test
    @DisplayName("todo에 item들 저장")
    public void getTodosItems() {
        // Given
        User savedUser1 = userRepository.save(user1);
        Todo todo1 = Todo.builder()
                .user(savedUser1)
                .owner("leesh7807@gmail.com")
                .priority(0)
                .build();
        Todo savedTodo = todoRepository.save(todo1);
        TodoItem todoItem1 = TodoItem.builder()
                .todo(savedTodo)
                .status(Status.Pending)
                .exp(LocalDateTime.now())
                .priority(0)
                .content("")
                .build();
        TodoItem todoItem2 = TodoItem.builder()
                .todo(savedTodo)
                .status(Status.Pending)
                .exp(LocalDateTime.now())
                .priority(1)
                .content("")
                .build();

        // When
        todoItemRepository.save(todoItem1);
        todoItemRepository.save(todoItem2);
        Optional<User> retrievedUser = userRepository.findById(savedUser1.getUserId());

        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getTodos().get(0).getTodoItems()).isNotEmpty();
    }
}
