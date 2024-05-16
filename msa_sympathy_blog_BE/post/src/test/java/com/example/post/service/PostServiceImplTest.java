package com.example.post.service;

import com.example.post.dto.request.PostRequest;
import com.example.post.dto.response.PostResponse;
import com.example.post.global.domain.entity.Category;
import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.PostView;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.CategoryRepository;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.global.domain.repository.PostViewRepository;
import com.example.post.global.domain.repository.UserBlogRepository;
import com.example.post.global.domain.type.PublicScope;
import com.example.post.global.domain.type.Topic;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostViewRepository postViewRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private UserBlogRepository userBlogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void save() {
        UserBlog user = UserBlog.builder()
                .nickname("test").build();
        userBlogRepository.save(user);
        Category category = Category.builder()
                .categoryName("일상")
                .userBlog(user)
                .build();
        categoryRepository.save(category);
        Post init = Post.builder()
                .title("title")
                .content("content")
                .userBlog(user)
                .createdAt(LocalDateTime.now())
                .publicScope(PublicScope.ALL)
                .topic(Topic.valueOf("LIFESTYLE"))
                .category(category)
                .build();

        postRepository.save(init);

        PostView postView = init.getPostView();
        if (postView == null) {
            postView = PostView.builder()
                    .post(init).build(); // PostView 객체가 없는 경우 새로 생성
        }

        postView.setView(10);
        postViewRepository.save(postView);
        assertEquals(init.getTitle(), "title");
        assertEquals(10,postViewRepository.findById(1L).get().getView());
    }

    @Test
    void update_성공() {
        Post init = Post.builder()
                .title("title")
                .content("content")
                .createdAt(LocalDateTime.now())
                .publicScope(PublicScope.ALL)
                .category(categoryRepository.findById(1L).get())
                .topic(Topic.valueOf("LIFESTYLE"))
                .build();
        postRepository.save(init);

        Post byId = postRepository.findById(1L).get();
        byId.setTitle("updated");

        assertEquals(byId.getTitle(), "updated");
    }

    @Test
    void update_실패() {
        Post init = Post.builder()
                .title("title")
                .content("content")
                .createdAt(LocalDateTime.now())
                .publicScope(PublicScope.ALL)
                .topic(Topic.valueOf("LIFESTYLE"))
                .category(categoryRepository.findById(1L).get())
                .build();
        postRepository.save(init);
        PostRequest req = new PostRequest("title", "dfsdf",
                UUID.randomUUID(),
                "ddd",Topic.LIFESTYLE, PublicScope.ALL, Category.builder().categoryName("Hola").build());
        assertThrows(EntityNotFoundException.class,
                () -> postService.update(req,100L));
    }

    @Test
    void getPostById_실패() {
        assertThrows(EntityNotFoundException.class,
                () -> postService.getPostById(100L));
    }

    @Test
    void getPostById_성공() {
        Post init = Post.builder()
                .title("title")
                .content("content")
                .createdAt(LocalDateTime.now())
                .publicScope(PublicScope.ALL)
                .category(categoryRepository.findById(1L).get())
                .topic(Topic.valueOf("LIFESTYLE"))
                .build();
        postRepository.save(init);

        Post byId = postRepository.findById(1L).get();
        assertEquals(byId.getTitle(), "title");
    }

    @Test
    @Transactional
    void getPostsByUserId() {
        UUID userId = UUID.fromString("de9518eb-5335-4593-8136-2b791d40827c");
        Pageable pageable = PageRequest.of(0, 5);

        // 새로운 UserBlog 객체 생성 및 저장
        UserBlog user = UserBlog.builder()
                .id(userId)
                .nickname("test")
                .build();

        for (Long i = 0L; i < 5; i++) {
            // Post 엔티티 생성
            Post init = Post.builder()
                    .title("title" + i)
                    .content("content")
                    .createdAt(LocalDateTime.now())
                    .publicScope(PublicScope.ALL)
                    .category(categoryRepository.findById(1L).get())
                    .topic(Topic.valueOf("LIFESTYLE"))
                    .userBlog(user)
                    .build();

            // PostView 객체 생성 및 설정
            PostView postView = PostView.builder().post(init).build();
            postView.setView(0);
            postViewRepository.save(postView);

            // Post 엔티티에 PostView 설정
            init.setPostView(postView);

            // 생성한 Post 엔티티 저장
            postRepository.save(init);
        }


        // 사용자 ID를 기반으로 게시물을 가져와서 검증
        Page<PostResponse> postResponses = postService.getPostsByUserId(pageable, userId);
        assertEquals(5, postResponses.getSize());

    }
}
