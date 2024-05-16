
package com.example.user.service;

import com.example.user.dto.request.UserBlogRequest;
import com.example.user.dto.response.SignInResponse;
import com.example.user.dto.response.UserBlogResponse;
import com.example.user.global.domain.entity.UserBlog;
import com.example.user.global.domain.repository.UserBlogRepository;
import com.example.user.kafka.dto.KafkaUserBlogDto;
import com.example.user.global.dto.UserBlogDto;
import com.example.user.global.utils.JwtUtil;
import com.example.user.kafka.dto.KafkaStatus;
import com.example.user.kafka.producer.UserBlogIdProducer;
import com.example.user.kafka.dto.KafkaPostDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserBlogServiceImpl implements UserBlogService, UserDetailsService {
    private final UserBlogIdProducer userBlogIdProducer;
    public final UserBlogRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }


    @Override
    public SignInResponse saveInfo(UserBlogDto req) {
        UserBlog userBlog = UserBlog.builder()
                .email(req.toEntity().getEmail())
                .nickname(req.toEntity().getNickname())
                .blogName(req.toEntity().getBlogName())
                .id(req.toEntity().getId())
                .build();

        UserBlog save = userRepository.save(userBlog);
        UserBlogDto userBlogDto = UserBlogDto.from(save);

        String token = jwtUtil.generateToken(userBlogDto);

        KafkaUserBlogDto kafkaInitDto =
                KafkaUserBlogDto.builder().userBlogId(req.userBlogId())
                        .nickname(req.nickname())
                        .build();
        KafkaStatus<KafkaUserBlogDto> kafkaStatus = new KafkaStatus<>(kafkaInitDto,"init");
        userBlogIdProducer.send(kafkaStatus.data(),"init");

        return SignInResponse.from(token);
    }

    @Override
    public UserBlog update(UserBlogRequest req, UUID id) {
        UserBlog userBlog = userRepository.findById(id).orElseThrow(
                EntityNotFoundException::new);
        userBlog.setNickname(req.nickname());
        userBlog.setBlogName(req.blogName());
        userRepository.save(userBlog);

        KafkaUserBlogDto kafkaUserBlogDto = new KafkaUserBlogDto(id.toString(),req.nickname());
        KafkaStatus<KafkaUserBlogDto> kafkaStatus = new KafkaStatus<>(kafkaUserBlogDto,"update");
        userBlogIdProducer.send(kafkaStatus.data(),"update");
        return userBlog;
    }

    public KafkaUserBlogDto deleteUserBlog(UserBlogRequest req, UUID id) {

        UserBlog userBlog = userRepository.findById(id).orElseThrow(
                EntityNotFoundException::new);

        userRepository.delete(userBlog);

        KafkaUserBlogDto kafkaUserBlogDto = new KafkaUserBlogDto(id.toString(),null);
        KafkaStatus<KafkaUserBlogDto> kafkaStatus = new KafkaStatus<>(kafkaUserBlogDto,"delete");
        userBlogIdProducer.send(kafkaStatus.data(),"delete");

        return kafkaUserBlogDto;
    }

    @Override
    public UserBlogResponse getUserBlogById(UUID id) {
        UserBlogResponse blogResponse = UserBlogResponse
                .from(userRepository.findAllById(id)
                        .orElseThrow(EntityNotFoundException::new));
        return blogResponse;
    }



    @KafkaListener(topics = "post-topic", id = "user")
    @Transactional
    public void listen(KafkaStatus<KafkaPostDto> dto) {
        if (dto.status().equals("insert")) {
            UserBlog user = userRepository
                    .findById(dto.data().userBlogId()).orElseThrow(EntityNotFoundException::new);
            user.setPostId(dto.data().id());
        }
    }

}
