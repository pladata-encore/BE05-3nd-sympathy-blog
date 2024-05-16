package com.example.user.global.domain.repository;

import com.example.user.global.domain.entity.Today;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodayRepository extends CrudRepository<Today, UUID> {

    Optional<Today> findByUserBlogIdAndDate(UUID userBlogId, LocalDate date);
    Optional<Today> findByDate(LocalDate date);
    List<Today> findByUserBlogId(UUID userBlogId);


}
