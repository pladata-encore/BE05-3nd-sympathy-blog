package com.example.comment.service.d2;

import com.example.comment.dto.request.CommentLikeRequest;
import com.example.comment.dto.request.CommentRequest;
import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;
import com.example.comment.global.domain.repository.CommentLikeRepository;
import com.example.comment.global.domain.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CommentLikeServiceImplTest {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final CommentLikeService commentLikeService;
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    @Autowired
    CommentLikeServiceImplTest(CommentRepository commentRepository, CommentLikeService commentLikeService) {
        this.commentRepository = commentRepository;
        this.commentLikeService = commentLikeService;
    }

    @Test
    void alreadyliked_likeComment() {
        UUID likerId = UUID.randomUUID();
        CommentRequest commentRequest =
                new CommentRequest(1l, UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        CommentLikeRequest request = new CommentLikeRequest(comment.getId(),likerId);
        CommentLike like =  request.toEntity();


        commentLikeRepository.save(like);


        int i = commentLikeService.likeComment(comment, likerId);
        System.out.println(i);

    }

    @Test
    void notliked_likeComment() {
        UUID likerId = UUID.randomUUID();
        CommentRequest commentRequest =
                new CommentRequest(1l, UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        CommentLikeRequest request = new CommentLikeRequest(comment.getId(),UUID.randomUUID());
        CommentLike like =  request.toEntity();


        commentLikeRepository.save(like);


        int i = commentLikeService.likeComment(comment, likerId);
        System.out.println(i);

    }
}