package com.training.order_events.service;

import com.training.order_events.model.OrderPlaced;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(queues = "OrderQueue")
    public void receiveOrder(OrderPlaced order) {
        System.out.println("Order Fulfilled: " + order);
    }
}
