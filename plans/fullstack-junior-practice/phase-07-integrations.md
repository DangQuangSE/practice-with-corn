# Phase 07 — Service and realtime integrations

**Stories:** P2 optional integrations; P1 independently testable exercises; P1 paired contract where applicable.
**Dependencies:** Phases 03–06. Provider fakes can be used before every earlier exercise is complete.

## Deliverables

- INT-01 external REST adapter: map requests/responses, bound timeouts/retries, translate provider failures.
- Email adapter work in FLOW-02/FLOW-10 uses a local mail sink; document local start/check commands.
- FLOW-12 notification lifecycle: template, persisted status/history, retry and duplicate suppression, with a fake/local delivery adapter.
- INT-03 Cloudinary signed-upload adapter and provider failure handling after FILE-01; FE-11 upload progress/errors/retry. Backend keeps API secret/signing authority; automated paths use a stub.
- INT-04 Redis cache-aside with TTL/invalidation; optional reuse for a simple rate-limit exercise. PostgreSQL remains source of truth.
- INT-05 RabbitMQ small producer/consumer workflow, acknowledgement, bounded retry and dead-letter observation. No outbox/exactly-once/clustering in junior core.
- INT-06 Spring-hosted WebSocket/STOMP event and Next browser client; local one-instance scope.
- JOB-01 with FE-16: a small scheduled expiry/reminder or async task with persisted/observable pending-running-done-failed status. Introduce basic Spring scheduling separately from queue delivery.
- SEC-09 OAuth mock/development-provider sign-in as Extension, without a required external account.
- INT-07 generic webhook contract/signature/secret handling; payment-specific callbacks are expanded in Phase 08.
- INT-08 optional error-reporting/observability adapter, using synthetic local events only.
- BE-16 rate-limit safe repeated requests as an independent optional module; use Redis only if the objective calls for it.

## Acceptance criteria

- Every external dependency has a local stub/emulator/fake path, reset instructions, and deterministic failure checks.
- Redis TTL/invalidation can be observed and stale values are not treated as authoritative writes.
- RabbitMQ ack, retry/dead-letter behavior is observable; documentation distinguishes broker delivery guarantees from application idempotency.
- FLOW-12 proves deduplication and history using deterministic fake delivery; JOB-01 exposes task status and is safe to retry. A scheduler exercise does not require RabbitMQ, and RabbitMQ delivery does not imply exactly-once processing.
- WebSocket server runs in Java and the Next app only acts as client; no persistent socket is assumed in a serverless Next route handler.
- Signed-upload and OAuth secrets remain server-side; real vendor account setup is optional.

## Design Constraints

- Keep each integration isolated and optional; do not force every service into a single Compose stack.
- Bound retry/timeouts and prevent secrets or personal data from being sent to observability tools.
- Avoid claims of exactly-once delivery or production-grade HA from a one-node practice setup.

## Quality and Testing State

- Quality: not evaluated
- Testing: not started
