package dev.practice.payments.provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("vnpay")
public class VnPayProviderAdapter implements PaymentProvider {
    @Override
    public PaymentInstruction create(PaymentRequest request) {
        // PAY-06: use current VNPAY request/return parameters and HMAC algorithm from official spec.
        throw new UnsupportedOperationException("TODO: implement optional VNPay adapter");
    }

    @Override
    public dev.practice.payments.domain.ProviderCallback verifyAndMapCallback(byte[] rawBody, String signature) {
        // Verify the exact signed field set and canonical ordering before accepting the IPN result.
        throw new UnsupportedOperationException("TODO: verify and map VNPay IPN");
    }
}
