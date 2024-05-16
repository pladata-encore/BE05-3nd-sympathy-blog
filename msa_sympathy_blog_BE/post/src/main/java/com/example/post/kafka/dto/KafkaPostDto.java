package com.example.post.kafka.dto;

import com.example.post.global.domain.entity.UserBlog;

import java.util.UUID;

public record KafkaPostDto(
        Long id,
        String title,
        String content,
        UUID userBlogID
) {
}
