package com.example.user.service;

import com.example.user.dto.request.VisitorRequest;
import com.example.user.global.domain.entity.Visitor;

import java.util.List;

public interface VisitorService {

    void save(VisitorRequest request);
    int showVisitor(VisitorRequest request);
}
