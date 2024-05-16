package com.example.post.global.domain.entity;


import com.example.post.global.domain.type.PublicScope;

import com.example.post.global.domain.type.Topic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "POSTS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublicScope(PublicScope publicScope) {
        this.publicScope = publicScope;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setPostView(PostView postView) {
        this.postView = postView;
    }

    @Column(name="POST_TITLE", nullable = false)
    private String title;

    @Column(name="POST_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;


    @Column(name="POST_PUBLIC_SCOPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PublicScope publicScope;


    @Column(name="POST_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public void setCategory(Category category) {
        this.category = category;
    }

    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @ManyToOne
    private Category category;

    public void setUserBlog(UserBlog userBlog) {
        this.userBlog = userBlog;
    }

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserBlog userBlog;


    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private PostView postView;

    @Column(name="POST_TOPIC", nullable = false)
    @Enumerated(EnumType.STRING)
    private Topic topic;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLove> postLoves;
}
