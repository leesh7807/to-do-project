package com.alltodo.todo.repository;

import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        // Given
        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .id(uuid)
                .email("leesh7807@gmail.com")
                .password("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .build();

        // When
        User savedUser = userRepository.save(user);
        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());

        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo("leesh7807@gmail.com");
    }
}
