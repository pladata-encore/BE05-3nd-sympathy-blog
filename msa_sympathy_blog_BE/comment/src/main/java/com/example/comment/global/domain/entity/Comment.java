package com.example.comment.global.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="COMMENTS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="COMMENT_ID")
    private Long id;

    @Setter
    @Column(name = "CONTENT")
    private String content;

    @Column(name="COMMENT_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "LIKE_COUNT")
    @Setter
    private Integer likeCount;

    @Setter
    @Column(name = "NICKNAME")
    private String nickname;

//    comment 지우면 commentLike도 지우기 위해 cascade 설정
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;


}
