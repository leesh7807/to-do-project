package com.alltodo.todo.repository;

import com.alltodo.todo.entity.User;
import com.alltodo.todo.fixture.entity.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest() {
        // given
        User user = UserFixture.createDefaultUser();

        // when
        User savedUser = userRepository.save(user);

        // then
        assertNotNull(savedUser);
        assertAll(
                () -> assertEquals(user.getEmail(), savedUser.getEmail()),
                () -> assertEquals(user.getPassword(), savedUser.getPassword())
        );
    }
}
