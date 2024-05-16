package com.example.post.dto.request;

import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.PostView;

import java.util.UUID;

public record PostViewRequest(
        Long postId
) {
    public PostView toEntity(){
        return PostView.builder()
                .post(Post.builder().id(postId).build())
                .view(0)
                .build();
    }
}
