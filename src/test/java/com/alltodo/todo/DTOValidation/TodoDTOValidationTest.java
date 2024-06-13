package com.alltodo.todo.DTOValidation;

import com.alltodo.todo.dto.TodoDTO;
import com.alltodo.todo.fixture.dto.TodoDTOFixture;
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
public class TodoDTOValidationTest {
    @Autowired
    private Validator validator;
    private TodoDTO todoDTO;

    @BeforeEach
    public void setUp() {
        todoDTO = TodoDTOFixture.createDefaultTodoDTO();
    }

    @Test
    public void accepted() {
        // when
        Set<ConstraintViolation<TodoDTO>> violations = validator.validate(todoDTO);

        // then
        assertFalse(violations.iterator().hasNext());
    }

    @Test
    public void whenIdIsNUll() {
        // given
        todoDTO.setTodoId(null);

        // when
        Set<ConstraintViolation<TodoDTO>> violations = validator.validate(todoDTO);
        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("새로고침 후 다시 시도해 주세요.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPriorityIsInvalid() {
        // given
        todoDTO.setPriority(null);

        // when
        Set<ConstraintViolation<TodoDTO>> violations = validator.validate(todoDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("새로고침 후 다시 시도해 주세요.", violations.iterator().next().getMessage())
        );
    }
}
