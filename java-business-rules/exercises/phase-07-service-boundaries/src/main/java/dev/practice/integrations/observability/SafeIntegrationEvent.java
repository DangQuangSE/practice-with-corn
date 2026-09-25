package dev.practice.integrations.observability;

import java.time.Instant;

/** Allowlisted operational fields only; never include payloads, credentials, tokens, or personal data. */
public record SafeIntegrationEvent(String integration, String operation, String outcome, String correlationId,
                                   Instant occurredAt, String safeErrorCode) {
}
