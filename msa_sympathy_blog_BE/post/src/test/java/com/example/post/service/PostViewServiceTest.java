package com.example.post.service;

import com.example.post.global.domain.entity.PostView;
import com.example.post.global.domain.repository.PostViewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostViewServiceTest {
    @Autowired
    private PostViewRepository postViewRepository;
    @Autowired
    private PostViewService postViewService;
    @Test
    void update() {
        PostView postView = postViewRepository.findById(1L).get();
        postView.setView(postView.getView()+10);
        postViewRepository.save(postView);
        assertEquals(10, postView.getView());

    }

    @Test
    void getPostViewByPostId() {
        PostView postView = postViewRepository.findById(1L).get();
        postView.setView(postView.getView()+10);
        postViewRepository.save(postView);
        Integer view = postViewRepository.findPostViewByPostId(1L).getView();
        assertEquals(10, view);
    }
}