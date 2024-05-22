package com.alltodo.todo.repository;

import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private final UUID uuid = UUID.randomUUID();
    private final UUID uuid2 = UUID.randomUUID();
    @BeforeEach
    @Transactional
    public void setUp() {
        User user = User.builder()
                .userId(uuid)
                .email("leesh7807@gmail.com")
                .password("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .build();
        User user2 = User.builder()
                .userId(uuid2)
                .email("leesh7807@naver.com")
                .password("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .build();
        userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(user2);
        Todo todo = Todo.builder()
                .user(user)
                .owner("leesh7807@gmail.com")
                .priority(1)
                .build();
        Todo todo2 = Todo.builder()
                .user(user)
                .owner("leesh7807@naver.com")
                .priority(2)
                .build();
//        todoRepository.save(todo);
//        todoRepository.save(todo2);
    }
    @Test
    public void saveAndFindUser() {
        //
        Optional<User> retrievedUser = userRepository.findById(uuid);

        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo("leesh7807@gmail.com");
    }

    @Test
    public void getUsersTodos() {
        // When
        Optional<User> retrievedUser = userRepository.findById(uuid);

        // Then
        assertThat(retrievedUser).isPresent();
        List<Todo> todos = retrievedUser.get().getTodos();
        assertThat(todos.get(0).getOwner()).isEqualTo("leesh7807@gmail.com");
        assertThat(todos.get(1).getOwner()).isEqualTo("leesh7807@naver.com");
    }
}
