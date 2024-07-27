package com.alltodo.todo;

import com.alltodo.todo.configuration.jwt.JwtUtil;
import com.alltodo.todo.fixture.entity.UserFixture;
import com.alltodo.todo.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtUtil jwtUtil;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void validToken() throws Exception {
        String validToken = jwtUtil.generateAccessToken("test@naver.com");
        given(userDetailsService.loadUserByUsername("test@naver.com")).willReturn(UserFixture.createDefaultUser());

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .header("Authorization", "Bearer " + validToken))
                .andExpect(status().isOk());
    }

    @Test
    public void expiredToken() throws Exception {
        String expiredToken = jwtUtil.generateExpiredAccessToken("test@naver.com");

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"error\": \"Token has expired\"}"));
    }
}
