# Phase 08 — Payment lifecycle and providers

**IDs:** PAY-01–PAY-06, BE-15, TEST-08 practice. **Core:** PAY-01/PAY-02 local fake. **Extensions:** provider callbacks, refunds/reconciliation, PayOS, VNPay. **Runtime:** Java 21, Spring Boot 4.1.1.

Start with the fake provider. This scaffold makes no network payment request and does not move money. PayOS/VNPay adapters are disabled unless their explicit Spring profile is selected; even then, they are TODOs until checked against current official specifications.

## Progression

1. **PAY-01:** persist provider-neutral pending request against a Java-owned order; derive amount/currency server-side; enforce stable idempotency key.
2. **PAY-02:** use the [payment contract](../../../docs/fullstack-junior-practice/contracts/PAY-01-to-06.md); render pending/success/failure/cancel. On browser return, call Java `GET /api/payments/{id}`. Ignore `?status=paid` as settlement proof.
3. **PAY-03:** verify provider signature against raw/canonical input, timestamp/replay window and stored order/amount/currency; dedupe provider event ID and transition state atomically.
4. **PAY-04:** add fake refund/cancel/reconciliation responses and a mismatch queue. Do not silently mark paid based on a redirect or provider timeout.
5. **PAY-05/06:** implement one adapter at a time using current official PayOS or VNPay docs. Credentials live only in ignored server environment; sandbox use is optional.

## Code work areas

- `provider/PaymentProvider.java`: provider-neutral port and canonical callback boundary.
- `provider/FakePaymentProvider.java`: local fake with no money movement.
- `provider/PayOsProviderAdapter.java`, `VnPayProviderAdapter.java`: optional TODO adapters.
- `domain/PaymentTransitionPolicy.java`: allowed states and duplicate/late callback policy.
- `service/PaymentWorkflowService.java`, `PaymentCallbackProcessor.java`, `PaymentReconciliationService.java`: order binding, idempotency, signature validation, and state changes.
- `api/PaymentController.java`: endpoints with authorization/error behavior left as an exercise.
- The webhook scaffold caps raw payloads at 64 KiB before parsing; retain this limit when implementing signature verification.

## Verify

From this directory run `mvn -DskipTests package`. The Next client also has an independent typecheck/build. Do not use real payment details, accounts, or live endpoints.

- [PayOS signature verification](https://payos.vn/docs/tich-hop-webhook/kiem-tra-du-lieu-voi-signature/)
- [PayOS test environment](https://payos.vn/docs/moi-truong-test/)
- [VNPay checksum algorithm migration](https://sandbox.vnpayment.vn/apis/docs/chuyen-doi-thuat-toan/changeTypeHash.html)
- [VNPay payment/IPN guide](https://sandbox.vnpayment.vn/apis/docs/thanh-toan-pay/pay.html)
