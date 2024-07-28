package com.alltodo.todo.repository;

import com.alltodo.todo.config.token_auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken, String> {
}
