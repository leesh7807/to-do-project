package com.alltodo.todo.DTOValidation;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
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
public class UserDTOValidationTest {
    @Autowired
    private Validator validator;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = UserDTOFixture.createDefaultUserDTO();
    }
    @Test
    public void whenEmailIsAccepted() {
        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertFalse(violations.iterator().hasNext());
    }

    @Test
    public void whenEmailIsToLong() {
        // given
        userDTO.setEmail("a".repeat(255) + "@naver.com");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("이메일 최대 길이는 255자를 넘을 수 없습니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenNotEmailForm() {
        // given
        userDTO.setEmail("a".repeat(250));

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("이메일 형식이어야 합니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPasswordIsAccepted() {
        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertFalse(violations.iterator().hasNext())
        );
    }

    @Test
    public void whenPasswordIsTooLong() {
        // given
        userDTO.setPassword("a".repeat(20) + "123!!");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드의 길이는 8자 이상 20자 이하입니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPasswordIsTooShort() {
        // given
        userDTO.setPassword("asd123!");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드의 길이는 8자 이상 20자 이하입니다.", violations.iterator().next().getMessage())
        );
    }
    @Test
    public void whenPasswordDontHaveAlphabet() {
        // given
        userDTO.setPassword("123456!!!");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드는 하나 이상의 영문자를 포함해야 합니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPasswordDontHaveNumber() {
        // given
        userDTO.setPassword("asdfgh!!!");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드는 하나 이상의 숫자를 포함해야 합니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPasswordDontHaveSpecialCharacter() {
        // given
        userDTO.setPassword("asdfgh123");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드는 하나 이상의 특수문자를 포함해야 합니다.", violations.iterator().next().getMessage())
        );
    }

    @Test
    public void whenPasswordHaveEmptySpace() {
        // given
        userDTO.setPassword("test123 !!");

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("패스워드는 공백을 포함할 수 없습니다.", violations.iterator().next().getMessage())
        );
    }
}
