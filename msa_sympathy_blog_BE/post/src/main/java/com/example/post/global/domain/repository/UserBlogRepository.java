package com.example.post.global.domain.repository;

import com.example.post.global.domain.entity.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserBlogRepository extends JpaRepository<UserBlog, UUID> {
    Optional<UserBlog> findByNickname(String nickname);
    List<UserBlog> findAllByNickname(String nickname);
}
