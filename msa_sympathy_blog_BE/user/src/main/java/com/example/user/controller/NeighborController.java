package com.example.user.controller;

import com.example.user.dto.request.NeighborRequest;
import com.example.user.global.domain.entity.Neighbor;
import com.example.user.service.NeighborService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neighbors")
public class NeighborController {

    private final NeighborService neighborService;

    @GetMapping("/{id}")
    public List<Neighbor> showNeighbor(@PathVariable("id")String userBlogId){
        NeighborRequest request = new NeighborRequest(userBlogId,null,null,null);
        List<Neighbor> neighbors = neighborService.showNeighbor(request);
        return neighbors;
    }
    @PostMapping("/request{requestId}/{userBlogId}")
    public void acceptNeighborRequest(@PathVariable("userBlogId")String userBlogId,@PathVariable("requestId")String requestId){
        NeighborRequest request = new NeighborRequest(userBlogId,requestId,null,null);
        neighborService.acceptNeighborRequest(request);
    }

    @PutMapping("/update{requestId}/{userBlogId}")
    public void addNeighbor(@PathVariable("userBlogId")String userBlogId,@PathVariable("requestId")String requestId){
        NeighborRequest request = new NeighborRequest(userBlogId,requestId,null,null);
        neighborService.addNeighbor(request);
    }

    @PutMapping("/delete{requestId}/{userBlogId}")
    public void deleteNeighbor(@PathVariable("userBlogId")String userBlogId,@PathVariable("requestId")String requestId){
        NeighborRequest request = new NeighborRequest(userBlogId,requestId,null,null);
        neighborService.deleteNeighbor(request);
    }


}
