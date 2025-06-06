package com.ekonuma.thepoc.spring.controllers;

import com.ekonuma.thepoc.spring.enums.FeatureFlag;
import com.ekonuma.thepoc.spring.services.SseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequestMapping("/sse")
@RestController
public class SseController {

    private final SseService service;

    public SseController(SseService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handleSse(HttpSession session) {
        if (FeatureFlag.SSE.isActive()){
            return service.subscribe(session.getId());
        }
        return null;
    }
}
