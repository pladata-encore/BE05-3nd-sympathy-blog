package com.example.post.service;

import com.example.post.dto.request.PostViewRequest;
import com.example.post.global.domain.entity.PostView;
import com.example.post.global.domain.repository.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostViewServiceImpl implements PostViewService {
    private final PostViewRepository postViewRepository;

    @Override
    public PostView update(Long postId) {
        PostView postView = postViewRepository.findByPostId(postId).orElseThrow(IllegalArgumentException::new);
        postView.setView(postView.getView()+1);
        postViewRepository.save(postView);
        return postView;
    }

    public Integer getPostViewByPostId(Long postId) {
        PostView postView = postViewRepository.findByPostId(postId).orElseThrow(IllegalArgumentException::new);
        return postView != null ? postView.getView() : 0;
    }

}
