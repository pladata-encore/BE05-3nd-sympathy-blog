package com.example.post.service;

import com.example.post.dto.response.ImageResponse;
import com.example.post.exception.ImageNotFoundException;
import com.example.post.exception.PostNotFoundException;
import com.example.post.global.domain.entity.Image;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.repository.ImageRepository;
import com.example.post.global.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    @Override
    public void save(Long postId, String path) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        imageRepository.save(Image.builder()
                .post(post)
                .path(path)
                .build());
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String newPath) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        image.setPath(newPath);
    }

    @Override
    public ImageResponse findImage(Long id) {
        Image image = imageRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return new ImageResponse(image.getId(), image.getPath());
    }
}
