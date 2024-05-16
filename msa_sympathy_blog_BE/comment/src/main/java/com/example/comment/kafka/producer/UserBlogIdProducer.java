package com.example.comment.kafka.producer;


import com.example.comment.kafka.dto.KafkaStatus;
import com.example.comment.kafka.dto.KafkaUserBlogDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBlogIdProducer {
    private final KafkaTemplate<String, KafkaStatus<KafkaUserBlogDto>> kafkaTemplate;
    String name ="userBlog-topic";

    @Bean
    private NewTopic newTopic(){
        return new NewTopic(name, 1, (short) 1);
    }

    public void send(KafkaUserBlogDto kafkaUserBlogDto, String status){
        KafkaStatus<KafkaUserBlogDto> kafkaStatus = new KafkaStatus<>(kafkaUserBlogDto, status);
        kafkaTemplate.send(name, kafkaStatus);
    }

}
