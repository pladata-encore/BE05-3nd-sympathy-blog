package com.example.comment.service.d2;

import com.example.comment.global.domain.entity.Comment;
import com.example.comment.global.domain.entity.CommentLike;
import com.example.comment.global.domain.repository.CommentLikeRepository;
import com.example.comment.global.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;



    @Override
    public int likeComment(Comment comment, UUID userId) {

        Optional<CommentLike> byCommentAndUserId = commentLikeRepository.findByCommentAndUserId(comment, userId);
        if (byCommentAndUserId.isPresent()) {
            commentLikeRepository.deleteById(byCommentAndUserId.get().getId());
            return -1;
        } else {
            commentLikeRepository.save(CommentLike.builder().userId(userId).comment(comment).isLiked(true).build());
            return +1;
        }
    }
}
