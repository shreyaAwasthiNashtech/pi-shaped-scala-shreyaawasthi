package com.training.order_events.model;

import lombok.Data;

@Data
public class OrderPlaced {
    private String orderId;
    private String customerId;
    private String product;
    private int quantity;
    private long timestamp;
}
