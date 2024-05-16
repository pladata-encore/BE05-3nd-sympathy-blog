package com.example.user.service;

import com.example.user.global.dto.UserBlogDto;

public interface TokenService {
    UserBlogDto getUserInfoFromToken(String token);
}