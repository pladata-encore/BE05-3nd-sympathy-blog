package com.example.user.dto.request;

import com.example.user.global.domain.entity.Visitor;


import java.util.UUID;

public record VisitorRequest(

        String userBlogId

) {
    public Visitor toEntity(){

        return Visitor.builder()
                .userBlogId(UUID.fromString(userBlogId))
                .build();
    }

}
