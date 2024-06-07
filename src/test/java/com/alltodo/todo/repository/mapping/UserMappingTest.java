package com.alltodo.todo.repository.mapping;

import com.alltodo.todo.configuration.SecurityConfig;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.Todo;
import com.alltodo.todo.entity.User;
import com.alltodo.todo.mapper.UserMapper;
import com.alltodo.todo.repository.fixture.dto.UserDTOFixture;
import com.alltodo.todo.repository.fixture.entity.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {SecurityConfig.class, UserMapper.class})
@SpringBootTest
public class UserMappingTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Test
    @DisplayName("UserDTO to User Entity")
    public void DTOToUserTest() {
        // given
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        List<Todo> todos = new ArrayList<>();

        // when
        User user = userMapper.toEntity(userId, userDTO, todos);

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
