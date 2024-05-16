package com.example.user.service;

import com.example.user.dto.request.TodayRequest;
import com.example.user.global.domain.entity.Today;
import com.example.user.global.domain.repository.TodayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodayServiceImpl implements TodayService{

    private final TodayRepository todayRepository;

    @Override
    public void save(TodayRequest request) {

        Optional<Today> byUserBlogIdAndDate =
                todayRepository.findByUserBlogIdAndDate(UUID.fromString(request.userBlogId()), request.date());
        if (byUserBlogIdAndDate.isEmpty()) {

            todayRepository.save(request.toEntity());
        } else {
            byUserBlogIdAndDate.get().setCount(byUserBlogIdAndDate.get().getCount() + 1);
                todayRepository.save(byUserBlogIdAndDate.get());
            }
        }



    @Override
    public int showCount(TodayRequest request) {

        Optional<Today> byUserBlogIdAndDate =
                todayRepository.findByUserBlogIdAndDate(UUID.fromString(request.userBlogId()), request.date());

        if(byUserBlogIdAndDate.isEmpty()){
            throw new IllegalArgumentException("오늘날짜 방문자가 없습니다.");
        }

        int count = byUserBlogIdAndDate.get().getCount();

        return count;
    }
}
