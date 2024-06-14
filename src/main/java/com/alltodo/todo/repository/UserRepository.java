package com.alltodo.todo.repository;

import com.alltodo.todo.entity.LoginMethod;
import com.alltodo.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAndLoginMethod(String email, LoginMethod loginMethod);
}
