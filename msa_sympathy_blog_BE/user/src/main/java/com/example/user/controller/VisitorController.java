package com.example.user.controller;

import com.example.user.dto.request.VisitorRequest;
import com.example.user.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping("/")
    public void saveVisitor(VisitorRequest visitorRequest){
        visitorService.save(visitorRequest);
    }

    @PostMapping("/")
    public int showVisitor(VisitorRequest visitorRequest){
        int i = visitorService.showVisitor(visitorRequest);
        return i;
    }
}
