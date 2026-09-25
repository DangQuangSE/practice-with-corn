# FLOW-07–FLOW-11 — Independent workflow extensions

These are separate small exercises and fixtures, not a shared product app.

- **FLOW-07 booking:** validate resource-local availability using `Instant` plus the resource timezone; reject overlapping intervals; cancellation releases the slot; define daylight-saving gaps/overlaps.
- **FLOW-08 subscription:** model trial, active, past-due, cancelled, and expired states; renewal/failure events are repeatable and transition-checked.
- **FLOW-09 approval:** only an authorized reviewer can approve/reject the current pending version; repeated and self-approval policies are explicit.
- **FLOW-11 audit (standalone):** record actor ID, timestamp, target type/ID, action, and a safe before/after summary. Exclude password, token, secret, payment credential, and raw file content.

Each extension documents inputs, state transitions, conflict/error shape, fake fixture, and deterministic boundary cases before implementation. Do not rely on wall-clock sleeps or production services.
