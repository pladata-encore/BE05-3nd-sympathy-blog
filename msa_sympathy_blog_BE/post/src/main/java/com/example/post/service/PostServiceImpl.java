package com.example.post.service;

import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;
import com.example.post.global.domain.entity.*;
import com.example.post.global.domain.repository.*;
import com.example.post.kafka.PostProducer;
import com.example.post.kafka.dto.KafkaPostDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final PostLoveRepository postLoveRepository;
    private final UserBlogRepository userBlogRepository;
    private final CategoryRepository categoryRepository;
    private final PostProducer producer;


    @Override
    public void save(PostRequest postRequest, UserBlog userBlog) {
        Post post = postRequest.toEntity();

        // 사용자 블로그 저장
        UserBlog user = userBlogRepository.save(userBlog);

        // 게시물과 사용자 블로그 연결
        post.setUserBlog(user);

        // 게시물 조회수 초기화
        PostView postView = post.getPostView();
        if (postView == null) {
            postView = PostView.builder().post(post).build(); // PostView 객체가 없는 경우 새로 생성
        }
        postView.setView(0);
        post.setPostView(postView);

        // 게시물 카테고리 처리
        Category category = post.getCategory();
        if (category != null) {
            Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
            if (existingCategory.isEmpty()) {
                // 새로운 카테고리 추가
                category.setUserBlog(user);
                category = categoryRepository.save(category);
            } else {
                category = existingCategory.get();
            }
            post.setCategory(category);
        }

        // 게시물 저장
        post = postRepository.save(post);

        // 게시물 좋아요 정보 추가
        UserBlog newUserBlog = userBlogRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        PostLove postLove = PostLove.builder().post(post).userBlog(newUserBlog).build();
        postLoveRepository.save(postLove);


        // user service에서 UserBlog entity의 postId update -> but, 방금 넣어준 post를 찾을 방법이 없다.
        List<Post> posts = postRepository.findAllByUserBlog(userBlog);
        Post recentPost = posts.get(posts.size()-1);
        KafkaPostDto kafkaPostDto = new KafkaPostDto(recentPost.getId()
                , recentPost.getTitle()
                , recentPost.getContent()
                , recentPost.getUserBlog().getId());
        producer.sendToUser(kafkaPostDto, "insert");



//        PostView postView = new PostView();
//        postView.setPost(post);
//        postView.setView(0);
//        postViewRepository.save(postView);

    }


    @Override
    public Post update(PostRequest req, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                EntityNotFoundException::new);
        post.setContent(req.content());
        post.setTitle(req.title());
//        post.setMediaPosts(req.toEntity().getMediaPosts()); // MediaPost table 삭제
        postRepository.save(post);

        return post;
    }

    @Override
    public PostResponse getPostById(Long id) {
        PostResponse post = PostResponse.from(postRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return post;
    }

//    pageable이 뭘까?
    @Override
    public Page<PostResponse> getPostsByUserId(Pageable pageable, UUID userId) {
        Page<Post> posts = postRepository.findAllByUserBlog_Id(pageable, userId);
        return posts.map(PostResponse::from);
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        postRepository.deleteById(id);
        KafkaPostDto kafkaPostDto = new KafkaPostDto(id, post.getTitle(), post.getContent(), post.getUserBlog().getId());
        producer.sendToComment(kafkaPostDto, "delete");
    }
}
