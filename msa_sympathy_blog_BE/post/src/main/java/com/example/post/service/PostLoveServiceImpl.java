package com.example.post.service;

import com.example.post.dto.response.LoveResponse;
import com.example.post.exception.NoLoverException;
import com.example.post.exception.PostLoveNotFoundException;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.PostLove;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.PostLoveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PostLoveServiceImpl implements PostLoveService {

    private final PostLoveRepository postLoveRepository;

    // post를 생성하면 바로 새로운 like entity를 만들어준다. (like = false로 해서)

    @Override
    public Long countLove(Long postId) {
        return postLoveRepository.countByPostIdAndLove(postId, true).orElseGet(() -> 0L);
    }

    @Override
    @Transactional
    public boolean updateLove(Long postId, UUID userId) {
        PostLove postLove = postLoveRepository
                .findByPostIdAndUserBlogId(postId, userId)
                .orElseThrow(PostLoveNotFoundException::new);
        postLove.setLove(!postLove.isLove());
        return postLove.isLove();
        // return 값에 따라, front에서 좋아요 수 +1, -1 결정
    }

    @Override
    public List<LoveResponse> getLovers(Long postId) {
        List<PostLove> postLoves = postLoveRepository.findByPostIdAndLove(postId, true);
        if (postLoves.isEmpty()) throw new NoLoverException(); // front에서 null이면 없음을 보여줌
        List<LoveResponse> list = new ArrayList<>();
        postLoves.forEach((el) -> {
            LoveResponse loveResponse = new LoveResponse(el.getUserBlog().getNickname(), el.getPost().getTitle());
            list.add(loveResponse);
        });
        return list;
    }
}
