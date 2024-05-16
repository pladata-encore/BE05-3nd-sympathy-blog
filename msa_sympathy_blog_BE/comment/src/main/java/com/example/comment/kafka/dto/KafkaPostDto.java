package com.example.comment.kafka.dto;

import java.util.UUID;

public record KafkaPostDto(
        Long id,
        String title,
        String content,
        UUID userBlogId
) {
}
