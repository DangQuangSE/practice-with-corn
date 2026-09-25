package dev.practice.payments.domain;

import java.time.Instant;

public record PaymentSummary(String paymentId, String orderId, long amountVnd, String currency,
                             PaymentStatus status, Instant updatedAt) {
}
