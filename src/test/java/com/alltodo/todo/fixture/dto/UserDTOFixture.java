package com.alltodo.todo.fixture.dto;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.entity.LoginMethod;

public class UserDTOFixture {
    private static final String defaultEmail = "test@naver.com";
    private static final String defaultPassword = "test123!!";
    private static final LoginMethod defaultLoginMethod = LoginMethod.EMAIL;
    public static UserDTO createDefaultUserDTO() {
        return UserDTO.builder()
                .email(defaultEmail)
                .password(defaultPassword)
                .loginMethod(defaultLoginMethod)
                .build();
    }
}
