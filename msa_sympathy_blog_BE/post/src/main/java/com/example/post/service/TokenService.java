package com.example.post.service;

import com.example.post.global.dto.UserBlogDto;
public interface TokenService {
    UserBlogDto getUserInfoFromToken(String token);
}