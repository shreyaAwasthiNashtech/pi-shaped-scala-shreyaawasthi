Core Concept Questions

1. What is the publish-subscribe model in Kafka?
The publish-subscribe (pub-sub) model in Kafka is a way of sharing messages between systems. A producer “publishes” messages to a topic, and one or more consumers “subscribe” to that topic to read the messages. This means many different applications can get the same data without depending on each other.

2. How does Kafka ensure message durability?
Kafka keeps messages safe by storing them on disk and copying them across multiple servers (brokers). Even if one server goes down, the data is still available from another copy. This makes sure messages are not lost.

3. What is the role of a Kafka topic and partition?
A topic is like a named channel where messages are grouped. Partitions are like slices of that topic that split the data into smaller parts. This helps in two ways:

It allows Kafka to handle a large amount of data by spreading it across many servers.

It lets multiple consumers read messages in parallel for faster processing.

4. What happens if a consumer fails while processing a message?
If a consumer fails, Kafka does not delete the message straight away. The message stays in the partition until another consumer in the same group picks it up. Kafka also uses something called “offsets” to remember where a consumer left off, so the next one can continue from the right spot.

5. Compare Kafka with another messaging system like RabbitMQ or MQTT.

Kafka is built for handling big streams of data with very high speed. It’s good for event-driven systems, logging, and analytics.

RabbitMQ works more like a traditional queue. It’s simple, reliable, and great when you need complex routing of messages.

MQTT is lightweight and often used for small devices like sensors in the Internet of Things (IoT).

In short: Kafka is best for large-scale, high-speed data pipelines. RabbitMQ is good for flexible message delivery. MQTT is best for small, low-power devices.