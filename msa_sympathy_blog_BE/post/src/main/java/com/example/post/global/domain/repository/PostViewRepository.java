package com.example.post.global.domain.repository;

import com.example.post.global.domain.entity.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    Optional<PostView> findByPostId(Long postId);

}
