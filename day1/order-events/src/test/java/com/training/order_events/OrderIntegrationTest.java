package com.training.order_events;

import com.training.order_events.model.OrderPlaced;
import com.training.order_events.service.OrderProducer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.RabbitMQContainer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
@Testcontainers
public class OrderIntegrationTest {

    @Container
    @ServiceConnection
    public static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.9-management");

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final AtomicBoolean messageConsumed = new AtomicBoolean(false);

    @Test
    public void testOrderPlacedEvent_sentAndReceived() {
        OrderPlaced order = new OrderPlaced();
        order.setOrderId("test-1");
        order.setCustomerId("cust-1");
        order.setProduct("item-1");
        order.setQuantity(1);
        order.setTimestamp(System.currentTimeMillis());

        orderProducer.sendOrder(order);

        System.out.println("Order placed event sent!");

        Object msg = rabbitTemplate.receiveAndConvert("OrderQueue", 5000);
        if (msg != null) {
            System.out.println("Message received from queue in test: " + msg);
            messageConsumed.set(true);
        }

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .untilTrue(messageConsumed);

        assert messageConsumed.get();
    }
}
