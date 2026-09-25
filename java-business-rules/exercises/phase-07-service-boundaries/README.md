# Phase 07 — Service boundaries and background work

**IDs:** INT-01, FLOW-02, FLOW-10, FLOW-12, JOB-01, SEC-09, INT-07, INT-08. **Runtime:** Java 21, Spring Boot 4.1.1.

This module uses local fakes. The one RestClient configuration sets explicit connect/read timeouts; the provider mapping, notification persistence/retry lifecycle, and durable scheduled-job claim remain TODOs. Other infrastructure adapters live in isolated modules below.

| ID | Work area | Learning boundary |
|---|---|---|
| INT-01 | `http/ExternalCatalogClient` | Map provider DTOs, cap retry, distinguish timeout/4xx/5xx, never retry non-idempotent commands blindly. |
| FLOW-02 | `mail/MailGateway`, `LocalMailSink` | Verification/reset tokens are random, one-time, expiring, hashed at rest, and never written to logs. Use Mailpit locally. |
| FLOW-10/FLOW-12 | `notification/NotificationAttemptService` | Persist state/history, suppress duplicates, limit retries, and expose failure status. No exactly-once claims. |
| JOB-01 / FE-16 | `jobs/` | A persisted pending/running/done/failed record, bounded scheduled batches, retry-safe operation, and a status endpoint. |
| SEC-09 | `oauth/DevelopmentIdentityProvider` | Fake only; exercise external subject validation/account linking separately, without a real account. |
| INT-07 | `webhook/WebhookSignatureVerifier` | Constant-time HMAC comparison is provided; canonicalization, timestamp skew/replay, and key rotation remain exercises. |
| INT-08 | `observability/SafeIntegrationEvent` | Add only allowlisted event metadata and safe error codes; do not send payloads or personal data. |
| BE-16 | See [Redis module](../phase-07-redis-cache/README.md) | Use Redis atomic operations, bounded keys, and explicit expiry for rate limiting. |

## Run and verify

From this directory: `mvn -DskipTests package`. No vendor credentials, database, broker, or live callback endpoint is needed to compile.

The local `support` services in the [Phase 06 operations lab](../../../devops/exercises/phase-06-operations/README.md) include PostgreSQL, Redis, and Mailpit. Only start the services needed for the exercise.
