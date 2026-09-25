package dev.practice.messaging.service;

import dev.practice.messaging.api.NotificationEvent;
import dev.practice.messaging.config.RabbitTopology;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {
    private final RabbitTemplate rabbit;

    public NotificationPublisher(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void publish(NotificationEvent event) {
        // INT-05: enable publisher confirms/returns and persist an outbox only as a later extension.
        rabbit.convertAndSend(RabbitTopology.EXCHANGE, "notification.created", event);
    }
}
