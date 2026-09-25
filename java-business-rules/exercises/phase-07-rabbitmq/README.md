# Phase 07 — RabbitMQ producer, consumer, retry, dead letter

**ID:** INT-05. **Tier:** Extension. **Difficulty:** Challenge. **Runtime:** Java 21, Spring Boot 4.1.1, RabbitMQ.

This broker exercise is isolated from the Redis and WebSocket modules. Use synthetic event IDs and the local RabbitMQ Compose service in this directory; no cloud broker is needed.

## Work areas

- Inspect the durable exchange/queue/dead-letter topology; decide retry queue/backoff and maximum deliveries.
- Add publisher confirms and returned-message handling. A successful method call alone is not proof the broker accepted the event.
- Configure manual acknowledgment, acknowledge only after durable application success, and reject/requeue intentionally.
- Make duplicate deliveries safe using `eventId`; distinguish broker delivery guarantees from application idempotency.
- Observe dead-letter messages and document a safe replay procedure. Do not add an outbox or claim exactly-once processing in this junior exercise.

## Verify

Copy `.env.example` to `.env`, set a unique local-only password, then run `docker compose up -d` and `docker compose ps` from this directory. The service is loopback-only and has a broker health check. Run `mvn -DskipTests package` to check compilation without connecting to RabbitMQ. Runtime work uses a disposable local queue and a deterministic fake notification handler.
