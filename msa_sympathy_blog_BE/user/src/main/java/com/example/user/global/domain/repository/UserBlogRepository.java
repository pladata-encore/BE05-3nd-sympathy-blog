package com.example.user.global.domain.repository;


import com.example.user.global.domain.entity.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserBlogRepository extends JpaRepository<UserBlog, UUID> {
    Optional<UserBlog> findByEmail(String email);
    Optional<UserBlog> findAllById(UUID uuid);
}
