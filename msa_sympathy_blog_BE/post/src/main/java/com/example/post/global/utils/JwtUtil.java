package com.example.post.global.utils;

import com.example.post.global.domain.entity.UserBlog;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtUtil {
    private final String secret;
    private final Long expiration;
    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.expiration}") Long expiration,
                   @Value("${jwt.secret}") String secret)
    {
        this.expiration = expiration;
        this.secret = secret;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public UserBlog getUUIDFromTokenAndValidate(String token) {
        Claims payload = (Claims) Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parse(token)
                .getPayload();

        String userId = payload.get("id", String.class);
        String nickname = (String) payload.get("nickname");
        return UserBlog.builder()
                .id(UUID.fromString(userId))
                .nickname(nickname).build();
    }


}
