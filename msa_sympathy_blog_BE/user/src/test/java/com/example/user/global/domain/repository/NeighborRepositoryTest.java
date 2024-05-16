package com.example.user.global.domain.repository;


import com.example.user.global.domain.entity.Neighbor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@Transactional
class NeighborRepositoryTest {
    @Autowired
    private NeighborRepository neighborRepository;


    @Test
    void findByResponseUserIdAndRequestUserId() {
        //given

        //when

        //then

    }

    @Test
    void findByType() {
        //given

        //when

        //then


    }
}