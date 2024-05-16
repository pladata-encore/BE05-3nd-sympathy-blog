package com.example.user.service;

import com.example.user.dto.request.NeighborRequest;
import com.example.user.global.domain.entity.Neighbor;
import com.example.user.global.domain.entity.Visitor;

import java.util.List;

public interface NeighborService {

    List<Neighbor> showNeighbor(NeighborRequest request);
    void addNeighbor(NeighborRequest request);
    void deleteNeighbor(NeighborRequest request);
    void acceptNeighborRequest(NeighborRequest request);


}
