package com.example.user.dto.request;

import com.example.user.global.domain.entity.Neighbor;
import com.example.user.global.domain.entity.UserBlog;

import java.util.UUID;

public record NeighborRequest(
        String userBlogId,
        String requestUserBlogId,
        String type,
        Boolean status
)


{


    public Neighbor toEntity(){
        return Neighbor.builder()
                .type(type)
                .requestUserBlogId(UUID.fromString(requestUserBlogId))
                .userBlog(UserBlog.builder().id(UUID.fromString(userBlogId)).build())
                .status(status)
                .build();
    }
}
