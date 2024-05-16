package com.example.user.global.domain.repository;

import com.example.user.global.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    //    select * from Team where leader = ? and secret = ?
    Optional<Team> findByLeaderAndSecret(String leader, String secret);
}
