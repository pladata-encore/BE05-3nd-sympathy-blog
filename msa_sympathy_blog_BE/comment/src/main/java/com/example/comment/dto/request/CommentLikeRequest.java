package com.example.comment.dto.request;

import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;

import java.util.UUID;

public record CommentLikeRequest(
        Long commentId,
        UUID userId


) {
    public CommentLike toEntity(){
        return CommentLike.builder()
                .userId(userId)
                .comment(Comment.builder().id(commentId).build())
                .build();
    }
}
