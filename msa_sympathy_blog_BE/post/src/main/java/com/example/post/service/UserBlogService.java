package com.example.post.service;

import com.example.post.kafka.dto.KafkaStatus;
import com.example.post.kafka.dto.KafkaUserBlogDto;

public interface UserBlogService {
    void init(KafkaStatus<KafkaUserBlogDto> status);
}
