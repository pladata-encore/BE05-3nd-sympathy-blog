package com.example.user.dto.request;

import com.example.user.global.domain.entity.UserBlog;

import java.util.UUID;

public record UserBlogRequest(
        String email, String nickname,
        String blogName
) {
    public UserBlog toEntity(UserBlog userBlog){
        return UserBlog.builder()
                .id(userBlog.getId())
                .email(email)
                .nickname(nickname)
                .blogName(blogName)
                .build();
    }
}
