package com.example.user.service;

import com.example.user.dto.request.NeighborRequest;
import com.example.user.global.domain.entity.Neighbor;
import com.example.user.global.domain.entity.UserBlog;
import com.example.user.global.domain.repository.NeighborRepository;
import com.example.user.global.domain.repository.UserBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NeighborServiceImpl implements NeighborService {
    private final NeighborRepository neighborRepository;
    private final UserBlogRepository userBlogRepository;


    //나한테 신청한 신청자들 보기
    @Override
    public List<Neighbor> showNeighbor(NeighborRequest request) {
        List<Neighbor> byUserBlogIds = neighborRepository.findByUserBlog_Id(UUID.fromString(request.userBlogId()));
        if(byUserBlogIds.isEmpty()){
            throw new IllegalArgumentException("신청하고자 하는 유저 블로그가 없습니다.");
        }
        return byUserBlogIds;
    }

    //이웃 신청
    @Override
    public void addNeighbor(NeighborRequest request){
        Optional<UserBlog> userBlog = userBlogRepository.findAllById(UUID.fromString(request.userBlogId()));
        if(userBlog.isEmpty()){
            throw new IllegalArgumentException("신청하고자 하는 유저 블로그가 없습니다.");
        }
        else {
            Neighbor newNeighbor = request.toEntity();
            newNeighbor.setType("이웃신청중");
            neighborRepository.save(newNeighbor);
        }
    }
    //이웃 신청자 삭제
    @Override
    public void deleteNeighbor(NeighborRequest request) {
        UUID requestUserId = request.toEntity().getRequestUserBlogId();
        UserBlog userBlog = request.toEntity().getUserBlog();
        Optional<Neighbor> acceptNeighbor = neighborRepository.findByUserBlog_IdAndRequestUserBlogId(userBlog.getId(),requestUserId);

        if(acceptNeighbor.isEmpty()){
            throw new IllegalArgumentException("블로그에 신청한 이력이 없습니다.");
        }else {
            neighborRepository.delete(acceptNeighbor.get());
        }


    }

    //이웃 신청 수락
        @Override
        public void acceptNeighborRequest(NeighborRequest request) {
            UUID requestUserId = request.toEntity().getRequestUserBlogId();
            UserBlog userBlog = request.toEntity().getUserBlog();
            Optional<Neighbor> acceptNeighbor = neighborRepository.findByUserBlog_IdAndRequestUserBlogId(userBlog.getId(), requestUserId);
            if (acceptNeighbor.isEmpty()) {
                throw new IllegalArgumentException("블로그에 신청한 이력이 없습니다.");
            } else {
                Neighbor neighbor = acceptNeighbor.get();
                neighbor.setStatus(true);
                neighbor.setType("이웃");
                neighborRepository.save(neighbor);
            }
        }




    }

