# Phase 08 — Payment lifecycle and provider adapters

**Stories:** P2 third-party payment practice; P1 safe local verification; P1 paired backend/frontend contract.
**Dependencies:** Phases 02 and 05; Phase 06 testing conventions are recommended but may be replaced by the exercise's documented local check. Phase 07 is optional and is not a prerequisite.

## Deliverables

- PAY-01 provider-neutral fake gateway: create request, pending state, verify result, transition order/payment state. No actual network payment required.
- PAY-02 paired UI for pending/success/failure/cancel/return states; refresh/query Java state rather than trusting redirect parameters.
- PAY-03 generic callback/webhook security: verify signature using provider-defined canonicalization and maintained crypto APIs; validate order/amount/currency; reject replay or handle duplicate idempotently; test malformed and duplicate events.
- PAY-04 optional cancellation/refund/reconciliation state machine with fake provider responses.
- PAY-05 optional PayOS adapter: payment-link request and callback/webhook handling. Learner may use a local fake; account/channel setup or vendor sandbox walkthrough is optional.
- PAY-06 optional VNPay adapter: request/return/IPN mapping and secure-hash validation; local fake covers automated checks; public endpoint/sandbox is not required.
- TEST-08 may add browser coverage against the fake lifecycle only; live money movement is prohibited.

The local fake (PAY-01) can be completed without Redis, RabbitMQ, Cloudinary, WebSocket, or any provider account. PAY-05 and PAY-06 depend only on completing PAY-01's provider-neutral contract and PAY-03's callback-verification concepts.

## Acceptance criteria

- Browser return/redirect alone never marks a payment paid; Java updates from verified server-to-server callback or trusted fake equivalent.
- Amount/order association and signature failures are covered. Duplicate callbacks do not double-apply state transitions.
- PayOS/VNPay specifics are adapter modules behind the provider-neutral contract; credentials are supplied only locally and are never committed or required.
- User-facing states and backend state transitions agree for success, pending, failure, cancel, and duplicate notification.

## Design Constraints

- No live transaction, real credential, production endpoint, or paid account required.
- Keep payment state transitions auditable and idempotent; do not store sensitive payment credentials.
- Provider documentation is version-sensitive: cite the official docs in the exercise and re-check signatures/parameters when implementing.

Preflight: Phase 05 models trusted order totals, state transitions, and idempotency; Phase 07 provides the generic webhook-signature boundary. Phase 08 starts with a fake provider; provider-specific callback canonicalization remains tied to the linked official docs.

## Quality and Testing State

- Quality: skipped by user for this implementation pass
- Testing: unit tests skipped by user; Maven package and Next.js typecheck/build passed
