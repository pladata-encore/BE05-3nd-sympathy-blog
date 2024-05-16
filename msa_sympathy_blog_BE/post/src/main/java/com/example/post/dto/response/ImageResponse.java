package com.example.post.dto.response;

import com.example.post.global.domain.entity.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ImageResponse(
        Long id,
        String path
) {
}
