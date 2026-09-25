# SEC-09, INT-07, INT-08, FLOW-02, FLOW-11

Optional focused exercises for OAuth, generic webhooks, observability, local email, and audit.

- **SEC-09:** mock provider first; map the validated external subject to an internal account. Validate issuer/audience/state/nonce in a real OAuth implementation; no live account required.
- **INT-07:** validate provider-defined signature/canonical bytes, timestamp/replay window, payload bound, event identity, and duplicate idempotency. Keep provider callback source server-side.
- **INT-08:** export allowlisted event metadata and safe error codes only. Exclude tokens, credentials, payment payload, file contents, and unnecessary personal data.
- **FLOW-02:** local mail sink only; one-use expiring verification/reset token is hashed in storage and never written to logs.
- **FLOW-11:** separate audit record captures actor/time/target/action and safe before/after values; exclude passwords, tokens, secrets, and raw payload.
