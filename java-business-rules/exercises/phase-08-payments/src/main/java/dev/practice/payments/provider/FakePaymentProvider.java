package dev.practice.payments.provider;

import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!payos & !vnpay")
public class FakePaymentProvider implements PaymentProvider {
    @Override
    public PaymentInstruction create(PaymentRequest request) {
        // PAY-01: this only creates a local reference; it performs no network request or money movement.
        String reference = "fake-" + UUID.randomUUID();
        return new PaymentInstruction(reference, "http://localhost:3000/practice-payment/" + reference);
    }

    @Override
    public dev.practice.payments.domain.ProviderCallback verifyAndMapCallback(byte[] rawBody, String signature) {
        throw new UnsupportedOperationException("TODO: accept deterministic fake events through a dev-only adapter");
    }
}
