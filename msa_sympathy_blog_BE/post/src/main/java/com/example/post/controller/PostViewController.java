package com.example.post.controller;

import com.example.post.dto.request.PostRequest;
import com.example.post.dto.request.PostViewRequest;
import com.example.post.service.PostViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostViewController {
    private final PostViewService postViewService;
    @GetMapping("/{id}/views")
    public Integer getPostViewByPostId(@PathVariable Long id) {
        return postViewService.getPostViewByPostId(id);
    }
    @PutMapping("/views/{postId}")
    public void update(@PathVariable Long postId) {
        postViewService.update(postId);
    }

}
