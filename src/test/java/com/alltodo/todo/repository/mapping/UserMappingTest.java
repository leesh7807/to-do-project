package com.alltodo.todo.repository.mapping;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.mapper.UserMapper;
import com.alltodo.todo.repository.fixture.dto.UserDTOFixture;
import com.alltodo.todo.repository.fixture.entity.UserFixture;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AllArgsConstructor
public class UserMappingTest {
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    @Test
    @DisplayName("UserDTO to User Entity")
    public void DTOToUserTest() {
        // given
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();

        // when
        User user = userMapper.toEntity(UUID.randomUUID(), userDTO, new ArrayList<Todo>());

        //then
        assertAll(
                () -> assertNotNull(user),
                () -> assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getEncryptedPassword()))
        );
    }

    @Test
    @DisplayName("User Entity to UserDTO")
    public void userToDTOTest() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        UserDTO userDTO = userMapper.toDTO(user);

        // then
        assertAll(
                () -> assertNotNull(userDTO),
                () -> assertEquals(user.getEncryptedPassword(), userDTO.getPassword())
        );
    }
}
