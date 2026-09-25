package dev.practice.payments.service;

import dev.practice.payments.domain.PaymentSummary;
import dev.practice.payments.provider.PaymentProvider;
import org.springframework.stereotype.Service;

@Service
public class PaymentWorkflowService {
    private final PaymentProvider provider;

    public PaymentWorkflowService(PaymentProvider provider) {
        this.provider = provider;
    }

    public PaymentSummary startForOrder(String orderId, String authenticatedAccountId, String idempotencyKey) {
        // PAY-01/PAY-02: load the order and owner in Java, recalculate the amount from trusted data,
        // persist PENDING before returning, and replay a stored result for a repeated idempotency key.
        throw new UnsupportedOperationException("TODO: create fake/provider-neutral payment request");
    }
}
