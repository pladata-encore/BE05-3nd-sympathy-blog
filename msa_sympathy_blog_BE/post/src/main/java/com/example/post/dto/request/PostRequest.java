package com.example.post.dto.request;

import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.type.PublicScope;
import com.example.post.global.domain.type.Topic;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostRequest(
//        postPublicScope, topic -> enum
//        userId -> UUID 사용 예정,
//        추후 카테고리 추가 예정
        String title,
        String content,
        Topic topic,
        PublicScope publicScope,
        Category category
) {
    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .publicScope(publicScope)
                .topic(topic)
                .category(category)
                .build();
    }
}
