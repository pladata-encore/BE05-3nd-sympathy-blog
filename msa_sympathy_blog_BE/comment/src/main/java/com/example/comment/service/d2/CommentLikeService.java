package com.example.comment.service.d2;

import com.example.comment.global.domain.entity.Comment;
import java.util.UUID;


public interface CommentLikeService {
    int likeComment(Comment comment, UUID userId);




}
