package com.example.post.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="CATEGORIES")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="CATEGORY_ID")
    private Long id;

    @Column(name="CATEGORY_NAME")
    @Setter
    private String categoryName;


    public void setUserBlog(UserBlog userBlog) {
        this.userBlog = userBlog;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserBlog userBlog;

}
