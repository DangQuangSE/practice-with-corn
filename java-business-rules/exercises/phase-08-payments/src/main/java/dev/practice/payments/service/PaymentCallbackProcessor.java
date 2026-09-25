package dev.practice.payments.service;

import dev.practice.payments.domain.PaymentSummary;
import dev.practice.payments.provider.PaymentProvider;
import org.springframework.stereotype.Service;

@Service
public class PaymentCallbackProcessor {
    private final PaymentProvider provider;

    public PaymentCallbackProcessor(PaymentProvider provider) {
        this.provider = provider;
    }

    public PaymentSummary accept(String paymentId, byte[] rawBody, String signature) {
        // PAY-03: verify signature first, then match stored order/payment/amount/currency, dedupe providerEventId,
        // transition state atomically, and acknowledge duplicates safely. Never trust the browser return URL.
        throw new UnsupportedOperationException("TODO: verify and apply payment callback");
    }
}
