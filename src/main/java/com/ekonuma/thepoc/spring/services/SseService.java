package com.ekonuma.thepoc.spring.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SseService {

    private final long TIMEOUT = -1L;
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final String sseTestMessage = "{ \"data\": \"Message sent to all listeners of \'%s\'': %s\" }";

    public SseEmitter subscribe(String sessionId) {
        var emitter = new SseEmitter(TIMEOUT);
        log.info("Client connected: " + sessionId);
        emitters.put(sessionId, emitter);
        emitter.onTimeout(() -> {
            emitters.remove(sessionId);
            log.info("Emitter timeout for client: " + sessionId);
            emitter.complete();
        });
        emitter.onCompletion(() -> {
            log.info("Emitter completed for client: " + sessionId);
            emitters.remove(sessionId);
        });
        return emitter;
    }

    public void send(String sessionId, String messageName, String message) {
        var emitter = emitters.get(sessionId);
        if (emitter != null) {
            try {
                log.info("Sending message to client: " + sessionId);
                emitter.send(SseEmitter.event().name(messageName).data(message));
            } catch (Exception e) {
                emitters.remove(sessionId);
                log.error("failed to send message to client: " + sessionId);
                emitter.complete();
            }
        }
    }

    @Scheduled(fixedDelay = 10000)
    @Async
    public void sendTestMessage() {
        emitters.keySet()
                .forEach(sessionId -> send(sessionId, "sse-test", String.format(sseTestMessage, "sse-test",
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))));
    }
}
