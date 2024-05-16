package com.example.post.kafka.dto;

public record KafkaBlogDto(
        long domainId, // postId
        int type, // 1 상품 2 블로그 3 카페 4 부동산
        String name, // title
        String description, // contents
        String url // front url of post page
) {

}
