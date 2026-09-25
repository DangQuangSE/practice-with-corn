package dev.practice.payments.domain;

import java.time.Instant;

/** Canonical internal callback. Provider adapters must verify/map their own format before creating this type. */
public record ProviderCallback(String providerEventId, String providerPaymentId, String orderId,
                               long amountVnd, String currency, PaymentStatus reportedStatus,
                               Instant occurredAt) {
}
