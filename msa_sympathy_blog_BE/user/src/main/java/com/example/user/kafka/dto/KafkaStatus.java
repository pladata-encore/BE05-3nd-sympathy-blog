package com.example.user.kafka.dto;
// kafka.dto.KafkaStatus
public record KafkaStatus<T>(
        T data, String status
) {
}
