# Phase 07 — Redis cache-aside and rate limits

**IDs:** INT-04, BE-16. **Tier:** Extension. **Runtime:** Java 21, Spring Boot 4.1.1, Redis 7.4.

This is a Redis-only runnable module; PostgreSQL remains the source of truth. Start only the Redis service from the [Phase 06 local support lab](../../../devops/exercises/phase-06-operations/README.md).

## Exercises

- **INT-04:** complete cache miss/source load/TTL behavior; invalidate after successful updates; define a safe fallback if Redis is unavailable; keep cache keys namespaced and values bounded.
- **BE-16:** implement an atomic fixed-window (or token-bucket) decision, expiry, opaque subject key, and fail-open/fail-closed policy. Do not use a racy read-then-increment sequence.

## Verify

Run `mvn -DskipTests package` from this directory. When Redis is available, inspect `TTL` and observe cache miss/hit/invalidation with synthetic product IDs. No credentials or unit tests are required in this implementation pass.
