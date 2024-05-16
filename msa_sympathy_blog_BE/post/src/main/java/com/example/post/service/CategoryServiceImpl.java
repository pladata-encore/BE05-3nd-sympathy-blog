package com.example.post.service;

import com.example.post.dto.response.CategoryResponse;
import com.example.post.exception.CategoryNotFoundException;
import com.example.post.exception.UserNotFoundException;
import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.CategoryRepository;
import com.example.post.global.domain.repository.UserBlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserBlogRepository userBlogRepository;

    @Override
    public List<Category> getAllByUserId(UUID userId) {
        return categoryRepository.findAllByUserBlog_Id(userId);
    }

    @Override
    public CategoryResponse getOne(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return new CategoryResponse(id, category.getCategoryName());
    }

    @Override
    public void create(String categoryName, UUID userId) {
        UserBlog userBlog = userBlogRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Category category = Category.builder().categoryName(categoryName).userBlog(userBlog).build();
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void update(Long id, String categoryName) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        category.setCategoryName(categoryName);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
