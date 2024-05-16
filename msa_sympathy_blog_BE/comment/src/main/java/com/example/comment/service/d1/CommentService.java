package com.example.comment.service.d1;

import com.example.comment.dto.request.CommentLikeRequest;
import com.example.comment.dto.request.CommentRequest;
import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface CommentService {
    void saveComment(CommentRequest request);
    void updateComment(CommentRequest request,Long id);
    void deleteComment(Long id);
    Comment getCommentId(Long id);
    void commentLike(CommentLike commentLike);
    int getCommentLikeTotalByCommentId(Long commentId);

}
