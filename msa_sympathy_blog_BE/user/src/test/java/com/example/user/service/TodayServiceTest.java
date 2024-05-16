package com.example.user.service;

import com.example.user.dto.request.TodayRequest;
import com.example.user.global.domain.entity.Today;
import com.example.user.global.domain.entity.UserBlog;
import com.example.user.global.domain.repository.TodayRepository;
import com.example.user.global.domain.repository.UserBlogRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodayServiceTest {

    @Autowired
    private TodayService todayService;
    @Autowired
    private TodayRepository todayRepository;
    @Autowired
    private final UserBlogRepository userBlogRepository;

    @Autowired
    TodayServiceTest(UserBlogRepository userBlogRepository) {
        this.userBlogRepository = userBlogRepository;
    }


    @Test
    void testSave() {

        UserBlog userBlog = UserBlog.builder().email("d@a.com").build();

       userBlogRepository.save(userBlog);

        Optional<UserBlog> byEmail = userBlogRepository.findByEmail("d@a.com");
        if(byEmail.isEmpty())
            throw new IllegalArgumentException("byEmail 비어져 있음");

        TodayRequest request = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));


         todayService.save(request);

        List<Today> byIds = todayRepository.findByUserBlogId(UUID.fromString(request.userBlogId()));
        if(byIds.isEmpty())
            throw new IllegalArgumentException("byId 비어있음");
        for(Today aa : byIds){
            System.out.println(aa.getId().toString());
            System.out.println(aa.getCount());

        }

        TodayRequest request1 = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));

        todayService.save(request1);


        List<Today> byUserBlogIds = todayRepository.findByUserBlogId(UUID.fromString(request.userBlogId()));

        if(byUserBlogIds.isEmpty())throw new IllegalArgumentException("못찾음");

        for(Today aa : byUserBlogIds){
            System.out.println(aa.getId().toString());
            System.out.println(aa.getCount());

        }



    }

    @Test
    void showCount() {



    }
}