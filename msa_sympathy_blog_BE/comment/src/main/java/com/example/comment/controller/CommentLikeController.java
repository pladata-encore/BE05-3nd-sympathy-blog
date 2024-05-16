package com.example.comment.controller;

import com.example.comment.global.domain.entity.Comment;
import com.example.comment.service.d2.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;
    @PostMapping("/{like}")
    public void likeComment(@PathVariable("userId") UUID userid, Comment comment) {
        commentLikeService.likeComment(comment, userid);
    }
}
