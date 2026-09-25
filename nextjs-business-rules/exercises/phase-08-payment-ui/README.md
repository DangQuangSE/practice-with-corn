# Phase 08 — Payment lifecycle UI

**IDs:** PAY-02, TEST-08 practice. **Runtime:** Node.js 24.21.0, Next.js 16.3.6, React 19.2, TypeScript 5.9.3.

The screen uses only a synthetic order and local state to teach pending/success/failure/cancel UX. It does not navigate to a real payment provider. The `status=paid` URL demonstration is intentionally ignored as settlement proof.

## Run and verify

```bash
npm ci
npm run dev
npm run typecheck
npm run build
```

## Exercises

- Connect the create button to Java's provider-neutral endpoint with an idempotency key; never send an amount from the browser as authoritative input.
- On return, take the payment ID from an allowlisted field and refetch status from Java; handle pending, expired, cancelled, failed, and success separately.
- Present accessible pending/error states and prevent duplicate clicks while the request is in flight.
- Keep callback signatures/credentials exclusively in Java. Fake-provider state transitions must be deterministic and resettable.
- Optional TEST-08: automate one fake-provider browser flow and a negative redirect-tampering flow; no live payments.
