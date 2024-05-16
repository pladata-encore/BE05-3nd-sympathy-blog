package com.example.post.service;

import com.example.post.dto.request.TeamRequest;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.dto.UserBlogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{
    private final RestTemplate restTemplate;
    @Override
    public UserBlogDto getUserInfoFromToken(String token) {
        TeamRequest request = new TeamRequest("김부자", "3345");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<TeamRequest> requestEntity = new HttpEntity<>(
                request,
                httpHeaders
        );
        httpHeaders.set("Authorization", token);

        UserBlog res = restTemplate
                .postForEntity(
                        "http://localhost:8089/api/v1/auth/token"
                        , requestEntity
                        , UserBlog.class
                ).getBody();
        System.out.println(res);
        return UserBlogDto.from(res);
    }
}