package com.pishaped.audit;

import com.pishaped.event.model.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEventConsumer {

    @KafkaListener(topics = "${app.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, UserEvent> record) {
        UserEvent event = record.value();
        log.info("[AUDIT] partition={}, offset={}, key={}, payload={}", record.partition(), record.offset(), record.key(), event);
    }
}
