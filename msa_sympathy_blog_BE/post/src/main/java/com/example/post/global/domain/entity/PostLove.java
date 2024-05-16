package com.example.post.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "POST_LOVES")
public class PostLove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_LOVE_ID")
    private Long id;

    @Column(name = "LOVE")
    @Setter
    private boolean love;

    // 객체로 받는다. 공감한 사람들을 보여줄 때 사용하기 때문에
    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;

    // 객체로 받는다. 공감한 사람들을 보여줄 때 사용하기 때문에
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private UserBlog userBlog;
}
