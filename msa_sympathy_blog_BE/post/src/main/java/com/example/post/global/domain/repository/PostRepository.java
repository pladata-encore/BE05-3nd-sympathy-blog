package com.example.post.global.domain.repository;

import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.UserBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByUserBlog_Id(Pageable pageable, UUID userId);
    List<Post> findAllByUserBlog(UserBlog userBlog);
}
