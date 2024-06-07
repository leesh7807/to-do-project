package com.alltodo.todo.repository.mapping;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.TodoItem;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.mapper.TodoMapper;
import com.alltodo.todo.repository.fixture.dto.TodoDTOFixture;
import com.alltodo.todo.repository.fixture.entity.TodoFixture;
import com.alltodo.todo.repository.fixture.entity.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {TodoMapper.class})
@SpringBootTest
public class TodoMappingTest {
    @Autowired
    private TodoMapper todoMapper;
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
}
