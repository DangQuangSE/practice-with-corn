package dev.practice.payments.provider;

import dev.practice.payments.domain.ProviderCallback;

public interface PaymentProvider {
    PaymentInstruction create(PaymentRequest request);

    /** Must verify provider signature before mapping an untrusted callback to a canonical event. */
    ProviderCallback verifyAndMapCallback(byte[] rawBody, String signature);

    record PaymentRequest(String orderId, long amountVnd, String currency, String idempotencyKey,
                          String returnUrl, String webhookUrl) {
    }

    record PaymentInstruction(String providerPaymentId, String checkoutUrl) {
    }
}
