package com.example.user.service;

import com.example.user.dto.request.NeighborRequest;
import com.example.user.global.domain.entity.Neighbor;
import com.example.user.global.domain.entity.UserBlog;
import com.example.user.global.domain.entity.Visitor;
import com.example.user.global.domain.repository.NeighborRepository;
import com.example.user.global.domain.repository.UserBlogRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class NeighborServiceTest {
    @Autowired
    private NeighborRepository neighborRepository;
    @Autowired
    private NeighborService neighborService;
    @Autowired
    UserBlogRepository userBlogRepository;


    @Test
    @Transactional
    void testAddNeighbor() {
        // given
        UserBlog userBlog = UserBlog.builder().build();

        userBlogRepository.save(userBlog);

        Optional<UserBlog> byId = userBlogRepository.findById(userBlog.getId());

        String uuid = byId.get().getId().toString();

        System.out.println(uuid);

        NeighborRequest neighborRequest = new NeighborRequest(
                uuid,
                UUID.randomUUID().toString(),
                null,
                null
        );

        // when

        neighborService.addNeighbor(neighborRequest);



        List<Neighbor> neighbors = neighborRepository.findByUserBlog_Id(UUID.fromString(uuid));

        // then
        for(Neighbor aa:neighbors){
            System.out.println(aa.getUserBlog().toString());
            System.out.println(aa.getType());
            System.out.println(aa.getStatus());


        }


    }


    @Test
    void 이웃삭제() {
        //given

        //when

        //then



    }

    @Test
    void 이웃수락() {
        //given

    }

    @Test
    void 이웃거절() {

        //given
        //when
        //then

    }
}
