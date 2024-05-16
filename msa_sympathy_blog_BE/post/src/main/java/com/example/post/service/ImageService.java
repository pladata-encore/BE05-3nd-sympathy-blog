package com.example.post.service;

import com.example.post.dto.response.ImageResponse;
import com.example.post.global.domain.entity.Image;


public interface ImageService {
    void save(Long postId, String path);
    void delete(Long id);
    void update(Long id, String newPath);
    ImageResponse findImage(Long id);
}
