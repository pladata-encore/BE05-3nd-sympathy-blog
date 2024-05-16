package com.example.comment.controller;

import com.example.comment.dto.request.CommentLikeRequest;
import com.example.comment.dto.request.CommentRequest;
import com.example.comment.global.domain.entity.Comment;
import com.example.comment.kafka.dto.KafkaPostDto;
import com.example.comment.kafka.dto.KafkaStatus;
import com.example.comment.service.d1.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public void save(@RequestBody CommentRequest request) {
        commentService.saveComment(request);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody CommentRequest request) {
        commentService.updateComment(request, id);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {

        return commentService.getCommentId(id);
    }
    @PostMapping("{id}/like")
    public void likeComment(@RequestBody  CommentLikeRequest request) {

        commentService.commentLike(request.toEntity());

    }
}
