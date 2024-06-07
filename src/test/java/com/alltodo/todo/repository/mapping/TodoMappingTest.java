package com.alltodo.todo.repository.mapping;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.mapper.TodoMapper;
import com.alltodo.todo.repository.fixture.dto.TodoDTOFixture;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AllArgsConstructor
public class TodoMappingTest {
    private TodoMapper todoMapper;
    @Test
    @DisplayName("TodoDTO to Todo Entity")
    public void DTOToTodoTest() {
        // given
        TodoDTO todoDTO = TodoDTOFixture.createDefaultTodoDTO();

        // when
    }

    @Test
    @DisplayName("Todo Entity to TodoDTO")
    public void TodoToDTOTest() {

    }
    public void toEntity() {

    }

    public void toDTO() {

    }
}
