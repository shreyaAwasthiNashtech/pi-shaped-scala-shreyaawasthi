package com.pishaped.user;

import com.pishaped.event.model.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private static final String TOPIC = "user-events";

    public void send(UserEvent event) {
        String key = event.getUserId();
        log.info("Publishing to topic={}, key={}, payload={}", TOPIC, key, event);
        kafkaTemplate.send(TOPIC, key, event);
    }
}
