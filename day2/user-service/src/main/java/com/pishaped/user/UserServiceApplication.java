package com.pishaped.user;

import com.pishaped.event.model.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication {

    private final UserEventProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner demo() {
        return args -> {
            for (int i = 1; i <= 5; i++) {
                UserEvent evt = new UserEvent(
                        "user_login",
                        UUID.randomUUID().toString(),
                        System.currentTimeMillis()
                );
                producer.send(evt);
                TimeUnit.SECONDS.sleep(1);
            }
        };
    }
}
