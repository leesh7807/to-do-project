package com.alltodo.todo.controller;

import com.alltodo.todo.config.token_auth.JwtRequestFilter;
import com.alltodo.todo.dto.AuthTokenDTO;
import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import com.alltodo.todo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    // Have to disabled for remove dependencies. Security filter chain isn't connected, so disabled all filters.
    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Test
    public void joinTest() throws Exception {
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        String userDTOJson = objectMapper.writeValueAsString(userDTO);
        doNothing().when(userService).join(any());

        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User join successfully."));
    }

    @Test
    public void loginTest() throws Exception {
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        String userDTOJson = objectMapper.writeValueAsString(userDTO);
        String userAgent = "user-agent";
        given(userService.login(any(), any())).willReturn(new AuthTokenDTO("accessToken", UUID.randomUUID()));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .header("user-agent", userAgent)
                .content(userDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User login successfully."))
                .andExpect(header().exists("Authorization"))
                .andExpect(header().exists("Refresh-Token"));
    }
}
