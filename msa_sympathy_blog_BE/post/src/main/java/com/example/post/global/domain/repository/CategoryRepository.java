package com.example.post.global.domain.repository;

import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.UserBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByUserBlog_Id(UUID userId);

    Optional<Category> findByCategoryName(String categoryName);
}
