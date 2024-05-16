package com.example.user.kafka.dto;

import lombok.Builder;

@Builder
public record KafkaUserBlogDto (

        String userBlogId,
        String nickname

){

}
