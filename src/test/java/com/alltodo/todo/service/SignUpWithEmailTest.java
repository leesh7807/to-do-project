package com.alltodo.todo.service;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SignUpWithEmailTest {
    @Autowired
    private UserRepository userRepository;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = UserDTOFixture.createDefaultUserDTO();
    }

    @Test
    public void accepted() {

    }
}
