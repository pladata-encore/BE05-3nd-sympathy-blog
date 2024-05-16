package com.example.post.service;

import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.UserBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface PostService {
//    글 쓰기, 삭제, 수정, 
//    글 하나씩 보기, 작성한 글 전체(목록) 보기
//    댓글 보기, 공감 수 보기, 조회수, 스크랩까지?
    void save(PostRequest postRequest, UserBlog userBlog);
    Post update(PostRequest req, Long id);

    PostResponse getPostById(Long id);
    Page<PostResponse> getPostsByUserId(Pageable pageable, UUID userId);

    void deleteById(Long id);
}
