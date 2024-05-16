package com.example.post.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Table(name = "POST_VIEWS")
public class PostView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_VIEW_ID")
    private Long id;
    @Column(name = "VIEW") @Setter
    private Integer view;

    @OneToOne
    @JoinColumn(name = "POST_ID")
    @Setter
    private Post post;

}
