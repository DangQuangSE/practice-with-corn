package dev.practice.payments.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentReconciliationService {
    public void reconcilePendingPayments() {
        // PAY-04: compare a bounded local pending set with a fake/provider query result;
        // record mismatches for review instead of silently forcing terminal state.
        throw new UnsupportedOperationException("TODO: implement reconciliation exercise");
    }
}
