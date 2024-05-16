package com.example.user.service;
import com.example.user.dto.request.VisitorRequest;
import com.example.user.global.domain.entity.Today;
import com.example.user.global.domain.entity.Visitor;
import com.example.user.global.domain.repository.TodayRepository;
import com.example.user.global.domain.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private final VisitorRepository visitorRepository;
    private final TodayRepository todayRepository;

    @Override
    public void save(VisitorRequest request) {

        List<Today> byUserBlogIdCount = todayRepository.findByUserBlogId(UUID.fromString(request.userBlogId()));

        if(byUserBlogIdCount.isEmpty()){
            throw new IllegalArgumentException("byUserBlogIdCount.isEmpty");
        }

        int count=0;
        for(Today aa:byUserBlogIdCount){

            count += aa.getCount();
        }
        Visitor visitor = Visitor.builder().count(count).userBlogId(UUID.fromString(request.userBlogId())).build();

        visitorRepository.save(visitor);
    }

    @Override
    public int showVisitor(VisitorRequest request) {

        Optional<Visitor> byUserBlogIdCount = visitorRepository.findByUserBlogId(UUID.fromString(request.userBlogId()));

        if(byUserBlogIdCount.isEmpty()){
            visitorRepository.save(request.toEntity());
        }

        return byUserBlogIdCount.get().getCount();
    }
}
