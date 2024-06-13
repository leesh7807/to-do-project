package com.alltodo.todo.DTOValidation;

import com.alltodo.todo.dto.TodoItemDTO;
import com.alltodo.todo.fixture.dto.TodoItemDTOFixture;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = LocalValidatorFactoryBean.class)
@SpringBootTest
public class TodoItemDTOValidationTest {
    @Autowired
    private Validator validator;
    private TodoItemDTO todoItemDTO;

    @BeforeEach
    public void setUp() {
        todoItemDTO = TodoItemDTOFixture.createDefaultTodoItemDTO();
    }

    @Test
    public void accepted() {
        // when
        Set<ConstraintViolation<TodoItemDTO>> violations = validator.validate(todoItemDTO);

        // then
        assertFalse(violations.iterator().hasNext());
    }

    @Test
    public void whenIdIsNUll() {
        // given
        todoItemDTO.setItemId(null);

        // when
        Set<ConstraintViolation<TodoItemDTO>> violations = validator.validate(todoItemDTO);
        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("새로고침 후 다시 시도해 주세요.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPriorityIsInvalid() {
        // given
        todoItemDTO.setPriority(null);

        // when
        Set<ConstraintViolation<TodoItemDTO>> violations = validator.validate(todoItemDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("새로고침 후 다시 시도해 주세요.", violations.iterator().next().getMessage())
        );
    }
}
