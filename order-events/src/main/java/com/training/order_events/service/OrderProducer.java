package com.training.order_events.service;

import com.training.order_events.model.OrderPlaced;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderPlaced order) {
        rabbitTemplate.convertAndSend("OrderQueue", order);
    }
}
