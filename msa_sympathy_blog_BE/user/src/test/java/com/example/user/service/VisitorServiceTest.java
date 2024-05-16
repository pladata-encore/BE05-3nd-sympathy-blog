package com.example.user.service;

import com.example.user.dto.request.TodayRequest;
import com.example.user.dto.request.VisitorRequest;
import com.example.user.global.domain.entity.Today;
import com.example.user.global.domain.entity.UserBlog;
import com.example.user.global.domain.entity.Visitor;
import com.example.user.global.domain.repository.TodayRepository;
import com.example.user.global.domain.repository.UserBlogRepository;
import com.example.user.global.domain.repository.VisitorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VisitorServiceTest {
    @Autowired
    private final VisitorService visitorService;
    @Autowired
    private final VisitorRepository visitorRepository;

    @Autowired
    private final UserBlogRepository userBlogRepository;
    @Autowired
    private final TodayService todayService;
    @Autowired
    private final TodayRepository todayRepository;

    @Autowired
    VisitorServiceTest(VisitorService visitorService, VisitorRepository visitorRepository, UserBlogRepository userBlogRepository, TodayService todayService, TodayRepository todayRepository) {
        this.visitorService = visitorService;
        this.visitorRepository = visitorRepository;
        this.userBlogRepository = userBlogRepository;
        this.todayService = todayService;
        this.todayRepository = todayRepository;
    }


    @Test
    void save() {
        UserBlog userBlog = UserBlog.builder().email("d@a.com").build();

        userBlogRepository.save(userBlog);

        Optional<UserBlog> byEmail = userBlogRepository.findByEmail("d@a.com");
        if(byEmail.isEmpty())
            throw new IllegalArgumentException("byEmail 비어져 있음");

        TodayRequest request = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));

        todayService.save(request);

        TodayRequest request1 = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));

        todayService.save(request1);

        VisitorRequest visitorRequest = new VisitorRequest(byEmail.get().getId().toString());

        List<Today> byUserBlogIdCount = todayRepository.findByUserBlogId(byEmail.get().getId());

        if(byUserBlogIdCount.isEmpty()){
            throw new IllegalArgumentException("byUserBlogIdCount.isEmpty");
        }

        int count=0;
        for(Today aa:byUserBlogIdCount){

            count += aa.getCount();
            System.out.println(count);
            System.out.println("Today는 문제 아님");
        }
        Visitor visitor = Visitor.builder().count(count).userBlogId(byEmail.get().getId()).build();
        visitorRepository.save(visitor);

        Optional<Visitor> byUserBlogId = visitorRepository.findByUserBlogId(byEmail.get().getId());
        System.out.println(byUserBlogId.get().getCount());

    }

    @Test
    void showVisitor() {

        UserBlog userBlog = UserBlog.builder().email("d@a.com").build();

        userBlogRepository.save(userBlog);

        Optional<UserBlog> byEmail = userBlogRepository.findByEmail("d@a.com");
        if(byEmail.isEmpty())
            throw new IllegalArgumentException("byEmail 비어져 있음");

        TodayRequest request = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));

        todayService.save(request);

        TodayRequest request1 = new TodayRequest(byEmail.get().getId().toString(),
                LocalDate.of(2000, 4, 7));

        todayService.save(request1);

        VisitorRequest visitorRequest = new VisitorRequest(byEmail.get().getId().toString());
        visitorService.save(visitorRequest);

        Optional<Visitor> byUserBlogId = visitorRepository.findByUserBlogId(byEmail.get().getId());


        System.out.println(byUserBlogId.get().getId().toString());

        int i = visitorService.showVisitor(visitorRequest);


        System.out.println(i);
    }
}