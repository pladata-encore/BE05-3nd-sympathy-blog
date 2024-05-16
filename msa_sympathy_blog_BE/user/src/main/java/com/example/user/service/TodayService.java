package com.example.user.service;

import com.example.user.dto.request.TodayRequest;

public interface TodayService {

    void save(TodayRequest request);

    int showCount(TodayRequest request);

}
