# Phase 08 — Payment lifecycle and providers

Complete the provider-neutral fake before optional PayOS/VNPay adapter practice.

- [Java API, domain state machine, fake provider, PayOS/VNPay TODO adapters](../../java-business-rules/exercises/phase-08-payments/README.md)
- [Next.js pending/success/failure/cancel UI](../../nextjs-business-rules/exercises/phase-08-payment-ui/README.md)
- [PAY-01–PAY-06 shared API/state contract](contracts/PAY-01-to-06.md)
- [TEST-08 browser exercise](testing-practice.md#test-08--browser-e2e-extension--challenge)

## Security invariants

- Java derives payment amount/order ownership and is the sole authority for status.
- Redirect/return parameters do not prove payment; verify provider callback or query server-side.
- Verify signatures against the exact current provider canonicalization and raw/decoded values; deduplicate provider events.
- Keep secrets in local server-only env; don't commit them or send them to Next.js.
- Fake/sandbox only; never run a live transaction.

Provider details are version-sensitive; re-read official references in the Java module README before implementing PayOS or VNPay.
