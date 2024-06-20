package com.alltodo.todo.repository;

import com.alltodo.todo.entity.TodoItem;
import com.alltodo.todo.fixture.entity.TodoItemFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoItemRepositoryTest {
    @Autowired
    TodoItemRepository todoItemRepository;

    @Test
    public void saveTodoItemTest() {
        // given
        TodoItem todoItem = TodoItemFixture.createDefaultTodoItem();

        // when
        TodoItem savedTodoItem = todoItemRepository.save(todoItem);

        // then
        assertNotNull(savedTodoItem);
        assertAll(
                () -> assertEquals(todoItem.getContent(), savedTodoItem.getContent()),
                () -> assertEquals(todoItem.getExp(), savedTodoItem.getExp())
        );
    }
}
