package com.example.user.kafka.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record KafkaInitDto(
       String id,
       String nickname

) {
}
