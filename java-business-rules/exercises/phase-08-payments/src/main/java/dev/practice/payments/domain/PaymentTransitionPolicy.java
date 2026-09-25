package dev.practice.payments.domain;

public class PaymentTransitionPolicy {
    public PaymentStatus transition(PaymentStatus current, PaymentStatus requested) {
        // PAY-01/PAY-04: define allowed transitions. Duplicate same-event callback is idempotent;
        // a late FAILED event must never move SUCCEEDED back to FAILED.
        throw new UnsupportedOperationException("TODO: implement payment state machine");
    }
}
