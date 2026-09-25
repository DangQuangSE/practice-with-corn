# PAY-01–PAY-06 — Provider-neutral payment lifecycle

**Owners:** Java calculates and persists payment/order state; Next.js presents checkout and status. Fake/local mode is the default.

## Core API

- `POST /api/orders/{orderId}/payments`, authenticated owner, `Idempotency-Key` required. The request does not accept amount/currency as truth; Java derives them from the order.
- `201` returns a typed payment summary and a fake/local checkout instruction with `status=PENDING`. Repeating the same key/request returns the stored result; different payload with the same key conflicts.
- `GET /api/payments/{paymentId}` checks ownership and returns backend state: `PENDING|SUCCEEDED|FAILED|CANCELLED|REFUNDED`.
- Browser return is presentation only. The client refetches this GET endpoint; query-string success does not settle payment.

## Callback and state rules

- `POST /api/payments/webhooks/{provider}` consumes bounded raw bytes and provider signature; adapter verifies signature/canonical field set before mapping to a provider-neutral event.
- Validate provider payment reference, internal order ID, expected amount/currency, event timestamp, and replay/idempotency key before state mutation.
- Apply callback and state transition atomically. Duplicate event returns a stable acknowledgement without applying twice. Late contradictory events never downgrade a valid terminal state.
- Provider timeout/redirect is not proof of success. Use a trusted callback or server-side provider query; keep reconciliation mismatches visible for review.

## Provider extensions

- PAY-05 PayOS: optional payment-link adapter and webhook signature validation, using current official API documentation.
- PAY-06 VNPay: optional request/return/IPN adapter, sorted canonical parameters, secure hash verification, and response mapping based on current merchant specification.
- Fake provider is the automated/local path. Never require live money, secrets in Git, or a public endpoint to complete core exercises.

## UI states

Pending, success, failure, cancellation, expired/unknown, provider unavailable, and retryable request state are distinct. Display amount/currency from Java response and never trust browser-supplied payment state.
