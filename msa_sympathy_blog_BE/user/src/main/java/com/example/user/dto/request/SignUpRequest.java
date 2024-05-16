package com.example.user.dto.request;

import com.example.user.global.domain.entity.UserBlog;

public record SignUpRequest(
        String password,
        String email
) {
    public UserBlog toEntity(String encodedPassword){
        return UserBlog.builder()
                .email(email)
                .build();
    }
}