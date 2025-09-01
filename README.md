# Order Events - Event-Driven Architecture with Spring Boot & RabbitMQ
**Project Overview**
This project demonstrates a simple event-driven e-commerce order processing system using Spring Boot and RabbitMQ. It includes:
- Producer: Generates and sends "OrderPlaced" events when orders are placed.
- RabbitMQ Broker: Routes events asynchronously through a message queue.
- Consumer: Listens for "OrderPlaced" events and simulates order fulfillment by logging details.
The system showcases asynchronous communication and loose coupling principles of event-driven architecture.

**Prerequisites**
Java 17 or later installed
Maven installed
Docker installed and running (to run RabbitMQ broker container for production or tests)
RabbitMQ broker running locally on default port 5672 (Docker command below)

**Setup and Running the Application**
1. Start RabbitMQ Broker using Docker (if not already running):
   docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management
You can check RabbitMQ Management UI at: http://localhost:15672 (default guest/guest)

2. Build and run Spring Boot application
In project root directory:
  mvn clean spring-boot:run
This starts the application on port 8080

**Using the Application**
Place a new order (send event):
- Use curl or Postman to send a POST request:
  curl -X POST http://localhost:8080/orders \
 -H "Content-Type: application/json" \
 -d '{"orderId":"101","customerId":"C1","product":"Book","quantity":2}'
 
- You will see console logs indicating:
  "Order placed event sent!"
  "Order Fulfilled: ... " for the order processed asynchronously by the consumer

 # Core Concepts in Event-Driven Architecture (EDA)
**1. What is an event in Event-Driven Architecture?**

An event is something that happens, a change or an action that is important to the system. Think of it like a real-world situation:

Imagine a shop assistant ringing up a sale on the till. That moment the sale happens is an event. This event can then tell other parts of the shop, like the stock room to update the inventory, or the till to print a receipt.

**2. Compare Event-Driven Architecture (EDA) with Request–Response Architecture**

**Request–Response**
This is the most common style of interaction. One service (the client) sends a request, and another service (the server) replies with a response.

Example: A user clicks "View Profile". The frontend asks the backend for the profile, and the backend sends it back.
It’s direct and predictable, but the client depends on the server being available and quick.
Think of it like ordering food at a restaurant: you place your order and wait until the waiter brings it to you.

**Event-Driven Architecture (EDA)**

In EDA, services don’t talk to each other directly. Instead, one service publishes an “event” and any number of other services can react to it.

Example: When an order is placed, one service charges the payment, another updates stock, and another sends an email – all triggered by the same “OrderPlaced” event.
This creates loose coupling, better scalability, and flexibility.
Think of it like a birthday party: someone announces “Cake is here!”, and whoever is interested (kids, adults, anyone hungry) reacts. The announcer doesn’t care who listens.

**3. Using EDA in an E-commerce Application**
Placing an order: When the customer places an order, an "OrderPlaced" event is created and sent.
Sending a confirmation email: A service listens for the "OrderPlaced" event and sends out a confirmation email asynchronously, without delaying the order.
Updating inventory: Another service listens for the same event to update stock counts, again working independently.
Each part reacts to events independently, making the system flexible and responsive.

**4. Why is EDA a good fit for Microservices and Cloud-Native systems?**
Independent Scalability: Each service reacts to events on its own, so services can be scaled individually as needed.
Loose coupling for better agility: Services are decoupled, making it easier to develop, deploy, and update services independently without breaking the entire system.

**5. How does EDA help build scalable systems?**
By decoupling parts of a system, EDA allows different components to process events in parallel without waiting on each other.
It also handles spikes of activity gracefully by queuing events for later processing.
Real-world Use Cases where EDA is better than monolithic systems:
Ride Hailing Apps: Multiple components react to passenger ride requests, driver locations, and payments as independent events.
Online Retail Systems: Sales trigger updates in payment service, inventory service, notification service independently, allowing smooth and scalable order processing.
