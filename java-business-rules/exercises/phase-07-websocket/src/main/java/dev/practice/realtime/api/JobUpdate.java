package dev.practice.realtime.api;

import java.time.Instant;

public record JobUpdate(String jobId, String status, int completedItems, Instant occurredAt) {
}
