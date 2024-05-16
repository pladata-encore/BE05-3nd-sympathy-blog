package com.example.post.service;

import com.example.post.dto.response.LoveResponse;

import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.PostLove;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.CategoryRepository;
import com.example.post.global.domain.repository.PostLoveRepository;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.global.domain.repository.UserBlogRepository;
import com.example.post.global.domain.type.PublicScope;
import com.example.post.global.domain.type.Topic;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostLoveServiceTest {

    @Autowired
    private PostLoveService postLoveService;

    @Autowired
    private UserBlogRepository userBlogRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostLoveRepository postLoveRepository;

    @Test
    void countLove() {
        UserBlog userBlog = userBlogRepository.findByNickname("wlshzz").get();
        Category category = categoryRepository.findById(6L).get();

        Post post = Post.builder()
                .title("title")
                .content("content")
                .userBlog(userBlog)
                .createdAt(LocalDateTime.now())
                .publicScope(PublicScope.ALL)
                .topic(Topic.valueOf("ENTERTAINMENT"))
                .category(category)
                .build();
        postRepository.save(post);

        PostLove postLove = PostLove.builder().post(post).userBlog(userBlog).love(true).build();
        postLoveRepository.save(postLove);
        Long count = postLoveService.countLove(post.getId());

        assertEquals(1, count);

    }

    @Test
    void updateLove() {
        Post post = postRepository.findById(4L).get();
        UserBlog userBlog = userBlogRepository.findByNickname("jinho").get();
        PostLove before = postLoveRepository.findByPostIdAndUserBlogId(post.getId(), userBlog.getId()).get();
        postLoveService.updateLove(post.getId(), userBlog.getId());
        PostLove after = postLoveRepository.findByPostIdAndUserBlogId(post.getId(), userBlog.getId()).get();

        assertEquals(!before.isLove(),after.isLove());
    }


    @Test
    void success() {
        Post post = postRepository.findById(4L).get();
        List<LoveResponse> posts = postLoveService.getLovers(post.getId());
        assertEquals(2, posts.size());
    }
}