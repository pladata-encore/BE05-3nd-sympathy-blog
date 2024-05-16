package com.example.user.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "VISITORS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "VISITOR_ID")
    private UUID id;
    @Column(name = "USER_Blog_ID")
    private UUID userBlogId;
    @Column( name = "VISITOR_COUNT")
    private int count;
    @Column( name = "VISITOR_DATE")
    private LocalDate date;

}
