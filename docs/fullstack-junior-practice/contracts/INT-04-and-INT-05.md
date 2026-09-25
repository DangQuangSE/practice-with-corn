# INT-04 / BE-16 — Redis and INT-05 — RabbitMQ

## Redis cache and rate limit

- PostgreSQL remains source of truth; cache has a namespaced key, bounded TTL, and explicit write invalidation.
- Define Redis outage behavior per operation. Never accept stale cache as authoritative for a payment/order/permission write.
- Rate limiting uses an atomic Redis operation and bounded key/cardinality policy; document fixed-window boundary and fail-open/closed behavior.

## RabbitMQ producer/consumer

- Producer publishes a durable event with stable `eventId`; configure confirm/return handling before claiming broker acceptance.
- Consumer acknowledges only after the application action has durably succeeded; duplicate delivery is expected and deduped at the application boundary.
- Configure a bounded retry/dead-letter route and document poison-event handling/replay. Do not confuse broker ack with exactly-once business processing.
- Local-only management interface is bound to loopback. Use synthetic events and resettable queues.
