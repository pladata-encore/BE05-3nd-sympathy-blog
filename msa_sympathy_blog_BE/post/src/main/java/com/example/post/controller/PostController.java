package com.example.post.controller;

import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;

import com.example.post.global.domain.entity.UserBlog;


import com.example.post.service.PostService;

import com.example.post.service.TokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;


import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private final PostService postService;
    private final TokenService tokenService;

    @PostMapping
    public void save(@AuthenticationPrincipal UserBlog user,
            @RequestBody PostRequest req) {
        postService.save(req,user);
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody PostRequest req) {
        postService.update(req, id);
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
    @GetMapping("user/{userId}")
    public Page<PostResponse> getPostsByUserId(@PathVariable UUID userId,
                                               @PageableDefault(
                                                       page = 0,
                                                       size=5,
                                                       sort = "createdAt",
                                                       direction = Sort.Direction.DESC
                                               ) Pageable pageable) {
        return postService.getPostsByUserId(pageable,userId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

}
