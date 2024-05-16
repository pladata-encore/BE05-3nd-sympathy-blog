package com.example.user.controller;

import com.example.user.dto.request.TodayRequest;
import com.example.user.global.domain.entity.Today;
import com.example.user.service.TodayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/today")
@RequiredArgsConstructor
public class TodayController {

    private final TodayService todayService;

    @GetMapping("/")
    public void saveToday(TodayRequest todayRequest){

        todayService.save(todayRequest);

    }

    @PostMapping("/")
    public int showToday(TodayRequest todayRequest){

        int i = todayService.showCount(todayRequest);

        return i;
    }





}
