package com.example.post.kafka.service;

import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.kafka.PostProducer;
import com.example.post.kafka.dto.KafkaBlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class KafkaService {
    private final PostProducer postProducer;
    private final PostRepository postRepository;

    @Scheduled(cron = "* */3 * * * *")
    public void send() {
        List<KafkaBlogDto> list = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        posts.forEach((el) -> {
            KafkaBlogDto kafkaBlogDto = new KafkaBlogDto(el.getId()
                    , 2
                    , el.getTitle()
                    , el.getContent()
                    , "https://blog.naver.com/post/" + el.getId());
            list.add(kafkaBlogDto);
        });
        postProducer.sendToAll(list, "blog-post");
    }
}
