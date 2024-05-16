package com.example.post.dto.response;

import com.example.post.global.domain.entity.PostView;

public record PostViewResponse(
        Long postId, Integer view
) {
    public static PostViewResponse from(PostView postView) {
        return new PostViewResponse(postView.getId(), postView.getView());
    }
}
