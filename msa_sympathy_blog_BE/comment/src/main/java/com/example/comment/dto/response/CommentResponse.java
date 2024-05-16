package com.example.comment.dto.response;

import java.util.UUID;

public record CommentResponse(
        UUID userId,
        String content,
        String nickname,
        int likeCount


) {
}
