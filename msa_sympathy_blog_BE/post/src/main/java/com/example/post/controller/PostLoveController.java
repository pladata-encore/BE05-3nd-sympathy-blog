package com.example.post.controller;

import com.example.post.dto.response.LoveResponse;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.utils.JwtUtil;
import com.example.post.service.PostLoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/postLikes")
public class PostLoveController {

    private final PostLoveService postLoveService;
    private final JwtUtil jwtUtil;


    @GetMapping("/{postId}") // 페이지 새로고침할 때 전체 갯수 가져오기
    public Long countLove(@PathVariable Long postId) {
        return postLoveService.countLove(postId);
    }

    @PutMapping("/{postId}") // 버튼 누르면 좋아요 변경하기
    public boolean updateLove(@PathVariable Long postId, @RequestHeader("Authorization") String bearerToken) {
        UUID userId = jwtUtil.getUUIDFromTokenAndValidate(bearerToken.substring(7)).getId();
        return postLoveService.updateLove(postId, userId);
    }

    @GetMapping("/lovers/{postId}") // 좋아요 누른 사람의 닉네임과 블로그 이름 가져오기
    public List<LoveResponse> getLovers(@PathVariable Long postId) {
        return postLoveService.getLovers(postId);
    }
}
