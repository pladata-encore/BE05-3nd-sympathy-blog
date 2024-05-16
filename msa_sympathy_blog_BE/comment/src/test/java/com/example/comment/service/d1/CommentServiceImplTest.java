package com.example.comment.service.d1;

import com.example.comment.dto.request.CommentLikeRequest;
import com.example.comment.dto.request.CommentRequest;
import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;
import com.example.comment.global.domain.repository.CommentRepository;
import com.example.comment.service.d2.CommentLikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentLikeService commentLikeService;

    @Autowired
    private CommentService commentService;

    @Test
    void saveComment() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
    }

    @Test
    void updateComment() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        Comment byId = commentRepository.findById(1l).get();
        CommentRequest updateRequest = new CommentRequest(comment.getPostId(),comment.getUserId(),"박분도할버지",comment.getNickname(),LocalDateTime.now());
        Comment updatedComment = updateRequest.toEntity();
        commentRepository.save(updatedComment);
    }

    @Test
    void deleteComment() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        commentRepository.delete(comment);

    }

    @Test
    void getByCommentId() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        Comment byId = commentRepository.findById(1l).get();
    }

    @Test
    void commentLike() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);

        CommentLikeRequest commentLikeRequest = new CommentLikeRequest(1l,UUID.randomUUID());
        CommentLike commentLike = commentLikeRequest.toEntity();
        commentService.commentLike(commentLike);






    }

    @Test
    void getCommentLikeTotalByCommentId() {
        CommentRequest commentRequest =
                new CommentRequest(1l,UUID.randomUUID(),"소성민할아버지","송송민", LocalDateTime.now() );
        Comment comment = commentRequest.toEntity();
        commentRepository.save(comment);
        Integer i = commentService.getCommentLikeTotalByCommentId(comment.getId());
       System.out.println(i);


    }
}