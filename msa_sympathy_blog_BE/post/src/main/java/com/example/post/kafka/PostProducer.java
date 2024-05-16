package com.example.post.kafka;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.kafka.dto.KafkaBlogDto;
import com.example.post.kafka.dto.KafkaPostDto;
import com.example.post.kafka.dto.KafkaStatus;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostProducer {
    private final KafkaTemplate<String, KafkaStatus<KafkaPostDto>> kafkaTemplateForUser;
    private final KafkaTemplate<String, KafkaStatus<List<KafkaBlogDto>>> kafkaTemplateForAll;


    @Bean
    public NewTopic postTopic() {
        return new NewTopic("post-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic blogTopic() {
        return new NewTopic("blog-topic", 1, (short) 1);
    }

    public void sendToUser(KafkaPostDto kafkaPostDto, String status) {
        KafkaStatus<KafkaPostDto> kafkaStatus = new KafkaStatus<>(kafkaPostDto, status);
        kafkaTemplateForUser.send("post-topic", kafkaStatus);
    }

    public void sendToAll(List<KafkaBlogDto> kafkaBlogDto, String status) {
        KafkaStatus<List<KafkaBlogDto>> kafkaStatus = new KafkaStatus<>(kafkaBlogDto, "blog-post");
        kafkaTemplateForAll.send("blog-topic", kafkaStatus);
    }

    public void sendToComment(KafkaPostDto kafkaPostDto, String status) {
        KafkaStatus<KafkaPostDto> kafkaStatus = new KafkaStatus<>(kafkaPostDto, status);
        kafkaTemplateForUser.send("post-topic", kafkaStatus);
    }

    public PostProducer(KafkaTemplate<String, KafkaStatus<KafkaPostDto>> kafkaTemplateForUser,
                        KafkaTemplate<String, KafkaStatus<List<KafkaBlogDto>>> kafkaTemplateForAll
                        ) {
        this.kafkaTemplateForUser = kafkaTemplateForUser;
        this.kafkaTemplateForAll = kafkaTemplateForAll;
    }
}
