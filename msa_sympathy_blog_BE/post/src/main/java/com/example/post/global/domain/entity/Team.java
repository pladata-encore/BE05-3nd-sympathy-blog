package com.example.post.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TEAMS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private UUID id;
    @Column(name = "TEAM_LEADER", unique = true)
    private String leader;
    @Column(name = "TEAM_SECRET")
    private String secret;

/*    요청 보낼 때
api/v1/auth/token/parse
method post
body{
    leader: "김부자",
    secret: "7984"
}
  * */
}
