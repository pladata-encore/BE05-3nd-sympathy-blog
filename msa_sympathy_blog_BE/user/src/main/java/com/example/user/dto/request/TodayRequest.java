package com.example.user.dto.request;

import com.example.user.global.domain.entity.Today;

import java.time.LocalDate;
import java.util.UUID;

public record TodayRequest(
        String userBlogId,
        LocalDate date
) {
    public Today toEntity(){

        return Today.builder()
                .userBlogId(UUID.fromString(userBlogId))
                .count(1)
                .date(date)
                .build();
    }
}
