package dev.practice.integrations.jobs;

import java.time.Instant;

public record JobRecord(String id, JobStatus status, Instant createdAt, Instant updatedAt, String safeFailureCode) {
}
