package com.example.post;

import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.kafka.PostProducer;
import com.example.post.kafka.dto.KafkaBlogDto;
import com.example.post.kafka.dto.KafkaPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootApplication
//@EnableFeignClients
//@EnableScheduling
public class PostApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostApplication.class, args);

	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public RecordMessageConverter converter(){
		return new JsonMessageConverter();
	}

}
