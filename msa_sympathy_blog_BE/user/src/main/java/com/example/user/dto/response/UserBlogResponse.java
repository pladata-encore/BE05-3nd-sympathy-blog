package com.example.user.dto.response;

import com.example.user.global.domain.entity.UserBlog;

public record UserBlogResponse(
        String id,
        String email,
        String nickname,
        String blogName
) {
    public static UserBlogResponse from(UserBlog res){
        return new UserBlogResponse(
                res.getId().toString(),
                res.getEmail(),
                res.getNickname(),
                res.getBlogName()
        );
    }
}
