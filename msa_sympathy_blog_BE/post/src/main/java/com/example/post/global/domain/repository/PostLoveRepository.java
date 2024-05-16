package com.example.post.global.domain.repository;

import com.example.post.global.domain.entity.PostLove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostLoveRepository extends JpaRepository<PostLove, Long> {
    Optional<Long> countByPostIdAndLove(Long postId, boolean love);
    Optional<PostLove> findByPostIdAndUserBlogId (Long postId, UUID userBlogId);
    List<PostLove> findByPostIdAndLove (Long postId, boolean love);
}
