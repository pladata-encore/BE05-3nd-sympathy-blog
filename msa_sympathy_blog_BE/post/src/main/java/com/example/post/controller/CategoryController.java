package com.example.post.controller;

import com.example.post.dto.response.CategoryResponse;
import com.example.post.global.domain.entity.Category;
import com.example.post.global.utils.JwtUtil;
import com.example.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final JwtUtil jwtUtil;

    @GetMapping // token 이용
    public List<Category> getAllByUserId(@RequestHeader("Authorization") String bearerToken) {
        UUID userId = jwtUtil.getUUIDFromTokenAndValidate(bearerToken.substring(7)).getId();
        return categoryService.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public CategoryResponse getOne(@PathVariable Long id) {
        return categoryService.getOne(id);
    }


    @PostMapping // token 이용
    public void create(@RequestBody String categoryName, @RequestHeader("Authorization") String bearerToken) {
        UUID userId = jwtUtil.getUUIDFromTokenAndValidate(bearerToken.substring(7)).getId();
        categoryService.create(categoryName, userId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody String categoryName) {
        categoryService.update(id, categoryName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
