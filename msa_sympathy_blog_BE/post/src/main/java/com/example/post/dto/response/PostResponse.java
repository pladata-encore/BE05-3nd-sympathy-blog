package com.example.post.dto.response;

import com.example.post.global.domain.entity.Post;

import java.time.LocalDateTime;

public record PostResponse(
//        카테고리, 미디어 추가 예정
//        댓글, 공감 어떻게 리스폰스 받을 건지 고민 됨
        String id, String title, String content, String userId,
        LocalDateTime createdAt, String topic, String publicScope,
        Integer view
//        , List<MediaPost> mediaPosts
) {
    public static PostResponse from(Post post) {
        Integer view = null;
        if (post.getPostView() != null) {
            view = post.getPostView().getView();
        }
        return new PostResponse(
                post.getId().toString(),
                post.getTitle(),
                post.getContent(),
                post.getUserBlog().getId().toString(),
                post.getCreatedAt(),
                post.getTopic().name(),
                post.getPublicScope().name(),
                view
        );
    }

}
