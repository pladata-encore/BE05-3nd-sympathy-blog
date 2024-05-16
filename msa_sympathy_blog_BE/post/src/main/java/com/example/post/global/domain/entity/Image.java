package com.example.post.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "IMAGES")
public class Image {

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "POST_ID")
    @ManyToOne
    private Post post;

    @Column(name = "PATH", nullable = false)
    @Setter
    private String path;

}
