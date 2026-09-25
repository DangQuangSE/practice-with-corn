package dev.practice.integrations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServiceBoundariesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceBoundariesApplication.class, args);
    }
}
