package com.example.post.service;


import com.example.post.dto.response.LoveResponse;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.UserBlog;

import java.util.List;
import java.util.UUID;

public interface PostLoveService {
    // 해당 post의 좋아요 수 가져오기
    // 새로 고침해서 들어갈 때 쓰기 위함
    Long countLove(Long postId);
    // 좋아요 클릭 기능 (클릭, 해제)
    // 프론트에서 좋아요 수랑, true/false알아서 바꿔줌
    boolean updateLove(Long postId, UUID userId);
    // 해당 post의 좋아요를 누른 사람의 nickname, blog명 return (이웃추가)
    List<LoveResponse> getLovers(Long postId);
}
