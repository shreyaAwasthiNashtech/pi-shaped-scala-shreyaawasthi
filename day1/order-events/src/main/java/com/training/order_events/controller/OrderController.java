package com.training.order_events.controller;

import com.training.order_events.model.OrderPlaced;
import com.training.order_events.service.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping
    public String testGet() {
        return "Order service is running. Use POST to place orders.";
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderPlaced order) {
        order.setTimestamp(System.currentTimeMillis());
        orderProducer.sendOrder(order);
        return "Order placed event sent!";
    }
}
