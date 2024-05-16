package com.example.user.kafka.dto;
import com.example.user.global.domain.entity.UserBlog;

import java.util.UUID;

public record KafkaPostDto(
        Long id,
        String title,
        String content,
        UUID userBlogId
) {
}
