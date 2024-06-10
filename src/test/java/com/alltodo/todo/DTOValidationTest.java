package com.alltodo.todo;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = LocalValidatorFactoryBean.class)
@SpringBootTest
public class DTOValidationTest {
    @Autowired
    private Validator validator;

    @Test
    @DisplayName("email > 255")
    public void whenEmailIsToLong() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
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
    @DisplayName("Not Email Form")
    public void whenNotEmailForm() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        userDTO.setEmail("a".repeat(250));

        // when
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // then
        assertAll(
                () -> assertNotNull(violations),
                () -> assertEquals("이메일 형식이어야 합니다.", violations.iterator().next().getMessage())
        );
    }
}
