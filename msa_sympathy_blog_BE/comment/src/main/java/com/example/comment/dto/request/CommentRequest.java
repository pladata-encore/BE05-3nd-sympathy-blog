package com.example.comment.dto.request;

import com.example.comment.global.domain.entity.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentRequest(
        Long postId,
        UUID userId,
        String content,
        String nickname,
        LocalDateTime createdAt

) {
    public Comment toEntity(){
        return Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .nickname(nickname)
                .createdAt(LocalDateTime.now())
                .likeCount(0)
                .build();
    }

}
