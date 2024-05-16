package com.example.comment.service.d2;

import com.example.comment.dto.request.CommentLikeRequest;
import com.example.comment.dto.request.CommentRequest;
import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;
import com.example.comment.global.domain.repository.CommentLikeRepository;
import com.example.comment.global.domain.repository.CommentRepository;
import com.example.comment.service.d1.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentLikeServiceTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentLikeService commentLikeService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikeRepository commentLikeRepository;


}