package com.alltodo.todo.repository;

import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.fixture.entity.TodoFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryTest {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void saveTodoTest() {
        // given
        Todo todo = TodoFixture.createDefaultTodo();

        // when
        Todo savedTodo = todoRepository.save(todo);

        // then
        assertNotNull(savedTodo);
        assertAll(
                () -> assertEquals(todo.getTitle(), savedTodo.getTitle())
        );
    }
}
