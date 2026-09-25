# INT-01, FLOW-02, FLOW-10/FLOW-12, JOB-01

These related exercises share reliability concepts but remain separate bounded adapters. Java owns provider calls and durable state; Next.js reads typed status and can run against mock responses.

## External HTTP — INT-01

- Configure provider base URL on the server; use explicit connect/read timeout and bounded response size.
- Map provider DTOs to an internal DTO; map timeout, rate-limit, unavailable, and invalid-response failures to safe application errors.
- Retry only bounded transient failures and only idempotent operations; use a stable idempotency key for commands that support it.
- Do not expose raw provider response/body or secrets to clients/logs.

## Email and notifications — FLOW-02, FLOW-10, FLOW-12

- Use a local mail sink for verification/reset messages. Link contains an opaque one-time expiring token; persist a hash, never log the raw token.
- Persist notification ID, type, attempts, safe error code, state, and timestamps. Duplicate request with the same key does not create duplicate delivery.
- Retry is bounded with backoff; permanent failure becomes observable and manually recoverable. No claim of exactly-once delivery.
- Keep template data allowlisted and avoid secrets/personal content in operational events.

## Background job — JOB-01 with FE-16

- `POST /api/jobs` returns `202` and `{ jobId, status: "PENDING" }`.
- `GET /api/jobs/{jobId}` returns persisted state `PENDING|RUNNING|COMPLETED|FAILED`, timestamps, and a safe failure code.
- Scheduled worker claims a bounded batch and persists `RUNNING` before work. Duplicate scheduling/retry is safe; do not keep long network work inside one DB transaction.
- WebSocket is an optional update signal; after reconnect the client refetches this HTTP resource.
