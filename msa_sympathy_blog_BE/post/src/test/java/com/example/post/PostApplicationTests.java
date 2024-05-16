package com.example.post;

import com.example.post.global.domain.entity.Post;
import com.example.post.global.domain.entity.PostLove;
import com.example.post.global.domain.entity.UserBlog;
import com.example.post.global.domain.repository.PostLoveRepository;
import com.example.post.global.domain.repository.PostRepository;
import com.example.post.global.domain.repository.UserBlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostApplicationTests {
	@Autowired
	PostLoveRepository postLoveRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserBlogRepository userBlogRepository;

	@Test
	void contextLoads() {
		Post test1 = postRepository.save(Post.builder()
				.title("test")
				.build());
		UserBlog test = userBlogRepository.save(UserBlog.builder()
				.nickname("test")
				.build());

		postLoveRepository.save(PostLove.builder()
						.userBlog(test)
						.post(test1)
				.build());

		List<PostLove> all = postLoveRepository.findAll();
		all.stream().forEach(e->{
			System.out.println(e.getPost().getId());
			System.out.println(e.getUserBlog().getId());
		});

	}

}
