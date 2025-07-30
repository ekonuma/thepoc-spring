package com.ekonuma.thepoc.spring.kafka.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaHandler {

    @KafkaListener(topics = "stock-topic", groupId = "payments-consumer-group")
    public void pizzaTopicHandler(String message){
        log.info("Evento recebido: {}", message);
    }
}
