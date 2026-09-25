package dev.practice.messaging.api;

import java.time.Instant;

public record NotificationEvent(String eventId, String notificationId, Instant createdAt) {
}
