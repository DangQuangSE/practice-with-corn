package dev.practice.payments.provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("payos")
public class PayOsProviderAdapter implements PaymentProvider {
    @Override
    public PaymentInstruction create(PaymentRequest request) {
        // PAY-05: use the current official API/SDK; map secrets from server-only environment configuration.
        throw new UnsupportedOperationException("TODO: implement optional PayOS payment-link adapter");
    }

    @Override
    public dev.practice.payments.domain.ProviderCallback verifyAndMapCallback(byte[] rawBody, String signature) {
        // Verify the exact current PayOS payment-request webhook signature before mapping any fields.
        throw new UnsupportedOperationException("TODO: verify and map PayOS webhook");
    }
}
