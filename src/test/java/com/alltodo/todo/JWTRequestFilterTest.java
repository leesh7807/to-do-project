package com.alltodo.todo;

import com.alltodo.todo.config.token_auth.JwtUtil;
import com.alltodo.todo.config.token_auth.RefreshToken;
import com.alltodo.todo.config.token_auth.RefreshTokenUtil;
import com.alltodo.todo.fixture.entity.RefreshTokenFixture;
import com.alltodo.todo.fixture.entity.UserFixture;
import com.alltodo.todo.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtUtil jwtUtil;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    RefreshTokenUtil refreshTokenUtil;

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
                .andExpect(content().json("{\"message\": \"Token expired\"}"));
    }

    @Test
    public void withRefreshToken() throws Exception {
        String expiredToken = jwtUtil.generateExpiredAccessToken("test@naver.com");
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        given(refreshTokenUtil.makeRefreshTokenKey(anyString(), anyString())).willReturn("key");
        given(refreshTokenUtil.validateRefreshToken(anyString(), any())).willReturn(true);
        given(refreshTokenUtil.generateUUIDRefreshToken()).willReturn(UUID.randomUUID());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + expiredToken);
        httpHeaders.set("Refresh-Token", refreshToken.getRefreshToken().toString());

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"message\": \"New tokens issued\"}"))
                .andExpect(header().exists("Authorization"))
                .andExpect(header().exists("Refresh-Token"));
    }

    @Test
    public void refreshTokenMismatch() throws Exception {
        String expiredToken = jwtUtil.generateExpiredAccessToken("test@naver.com");
        RefreshToken refreshToken = RefreshTokenFixture.createDefaultRefreshToken();
        given(refreshTokenUtil.validateRefreshToken(anyString(), any())).willReturn(false);
        given(refreshTokenUtil.generateUUIDRefreshToken()).willReturn(UUID.randomUUID());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + expiredToken);
        httpHeaders.set("Refresh-Token", refreshToken.getRefreshToken().toString());

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .headers(httpHeaders))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"message\": \"Error occurred. Please re-login\"}"));
    }
}
