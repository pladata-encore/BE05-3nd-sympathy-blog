package com.example.post.global.dto;

import com.example.post.global.domain.entity.UserBlog;

import java.util.UUID;

public record UserBlogDto(
        String id,
        String nickname
) {
    public UserBlog toEntity(){
        return UserBlog.builder()
                .id(UUID.fromString(id))
                .nickname(nickname)
                .build();
    }
    public static UserBlogDto from(UserBlog userBlog){
        return new UserBlogDto(
                userBlog.getId().toString(),
                userBlog.getNickname()
        );
    }
}
