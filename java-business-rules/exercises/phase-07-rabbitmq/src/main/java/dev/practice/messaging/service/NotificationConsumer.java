package dev.practice.messaging.service;

import dev.practice.messaging.api.NotificationEvent;
import dev.practice.messaging.config.RabbitTopology;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @RabbitListener(queues = RabbitTopology.QUEUE)
    public void consume(NotificationEvent event) {
        // TODO: acknowledge only after success, bound redelivery, classify poison messages, and dedupe by eventId.
        // A broker redelivery is possible; this consumer must tolerate duplicate application delivery.
    }
}
