package com.example.post.service;

import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.UserBlogRepository;
import com.example.post.kafka.dto.KafkaStatus;
import com.example.post.kafka.dto.KafkaUserBlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBlogServiceImpl implements UserBlogService, UserDetailsService {
    public final UserBlogRepository userBlogRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userBlogRepository
                .findById(UUID.fromString(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    @KafkaListener(topics = "userBlog-topic")
    public void init(KafkaStatus<KafkaUserBlogDto> status) {
        System.out.println(status.data());
        UserBlog userBlog =
                UserBlog.builder()
                        .id(UUID.fromString(status.data().userBlogId()))
                        .nickname(status.data().nickname())
                        .build();

        userBlogRepository.save(userBlog);
    }
}
