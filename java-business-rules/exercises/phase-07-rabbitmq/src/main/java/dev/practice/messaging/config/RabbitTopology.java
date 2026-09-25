package dev.practice.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RabbitTopology {
    public static final String EXCHANGE = "practice.events";
    public static final String QUEUE = "practice.notifications";
    public static final String DEAD_LETTER_EXCHANGE = "practice.events.dlx";

    @Bean
    DirectExchange practiceExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    @Bean
    Queue notificationQueue() {
        return QueueBuilder.durable(QUEUE)
                .deadLetterExchange(DEAD_LETTER_EXCHANGE)
                .deadLetterRoutingKey("notification.dead")
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable("practice.notifications.dead").build();
    }

    @Bean
    Binding notificationBinding(@Qualifier("notificationQueue") Queue notificationQueue,
                                @Qualifier("practiceExchange") DirectExchange practiceExchange) {
        return BindingBuilder.bind(notificationQueue).to(practiceExchange).with("notification.created");
    }

    @Bean
    Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue deadLetterQueue,
                              @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("notification.dead");
    }
}
