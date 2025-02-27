package com.ekonuma.thepoc.spring.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ekonuma.thepoc.spring.services.SseService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/sse")
@RestController
public class SseController {

    private SseService service;

    public SseController(SseService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handleSse(HttpSession session) {
        return service.subscribe(session.getId());
    }
}
