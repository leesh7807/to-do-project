package com.alltodo.todo.repository.fixture.dto;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;

public class UserDTOFixture {
    public static UserDTO createDefaultUserDTO() {
        return UserDTO.builder()
                .email("test@naver.com")
                .password("qwer1234")
                .loginMethod(LoginMethod.EMAIL)
                .build();
    }
}
