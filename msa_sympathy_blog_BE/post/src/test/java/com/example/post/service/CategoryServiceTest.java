package com.example.post.service;

import com.example.post.dto.response.CategoryResponse;
import com.example.post.exception.CategoryNotFoundException;
import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.CategoryRepository;
import com.example.post.global.domain.repository.UserBlogRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserBlogRepository userBlogRepository;


    @Test
    void create() {
        UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
        int length = categoryRepository.findAllByUserBlog_Id(userBlog.getId()).size();
        categoryService.create("New", userBlog.getId());
        Category category = categoryRepository.findByCategoryName("New").get();
        assertNotNull(category);
        assertEquals(length + 1, categoryRepository.findAllByUserBlog_Id(userBlog.getId()).size());
    }

    @Test
    void getAllByUserId() {
        UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
        categoryService.create("Travel", userBlog.getId());
        List<Category> categories = categoryService.getAllByUserId(userBlog.getId());
        assertNotEquals(0, categories.size());
    }

    @Nested
    class getOne {
        @Test
        void success() {
            UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
            categoryService.create("Food", userBlog.getId());
            List<Category> categories = categoryService.getAllByUserId(userBlog.getId());
            Category lastOne = categories.get(categories.size()-1);
            CategoryResponse categoryResponse = categoryService.getOne(lastOne.getId());
            assertEquals("Food", categoryResponse.categoryName());
        }
        @Test
        void CategoryNotFoundFail() {
            assertThrows(CategoryNotFoundException.class, () -> categoryService.getOne(1000L));
        }
    }

    @Nested
    class update {
        @Test
        void success() {
            UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
            categoryService.create("Fashion", userBlog.getId());
            List<Category> categories = categoryService.getAllByUserId(userBlog.getId());
            Category lastOne = categories.get(categories.size()-1);
            CategoryResponse categoryResponse = categoryService.getOne(lastOne.getId());
            categoryService.update(categoryResponse.id(), "Diary");
            String categoryName = categoryRepository.findById(categoryResponse.id()).get().getCategoryName();
            assertEquals("Diary", categoryName);
        }

        @Test
        void CategoryNotFoundFail() {
            assertThrows(CategoryNotFoundException.class
                    , () -> categoryService.update(1000L, "Fighting~"));
        }
    }

    @Nested
    class delete {
        @Test
        void success() {
            UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
            categoryService.create("Book", userBlog.getId());
            List<Category> categories = categoryService.getAllByUserId(userBlog.getId());
            Category lastOne = categories.get(categories.size()-1);
            CategoryResponse categoryResponse = categoryService.getOne(lastOne.getId());
            categoryService.delete(categoryResponse.id());
            assertTrue(categoryRepository.findById(categoryResponse.id()).isEmpty());
        }

        @Test
        void CategoryNotFoundFail() {
            assertThrows(CategoryNotFoundException.class
                    , () -> categoryService.delete(1000L));
        }
    }
}