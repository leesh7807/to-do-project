package com.alltodo.todo;

import com.alltodo.todo.dto.UserDTO;
import com.alltodo.todo.fixture.dto.UserDTOFixture;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JWTUtilTest {
    private final static byte[] bytes = new byte[32];
    private final static SecureRandom secureRandom = new SecureRandom();
    private static String JWT_SECRET;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10시간


    @BeforeAll
    public static void setUp() {
        secureRandom.nextBytes(bytes);
        JWT_SECRET = Base64.getEncoder().encodeToString(bytes);
    }
    @Test
    public void test() {
        UserDTO userDTO = UserDTOFixture.createDefaultUserDTO();
        String username = userDTO.getEmail();
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        // make jwt token
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

        // get claims
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();


        assertEquals(username, claims.getSubject(), "username 일치하나?");
        assertFalse(claims.getExpiration().before(new Date()), "토큰이 만료되었나?");

    }
}
