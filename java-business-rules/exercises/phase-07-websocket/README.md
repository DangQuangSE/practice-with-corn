# Phase 07 — Spring-hosted WebSocket/STOMP

**ID:** INT-06. **Tier:** Extension. **Difficulty:** Medium. **Runtime:** Java 21, Spring Boot 4.1.1.

Java hosts the local STOMP endpoint; Next.js is only the browser client. The in-memory simple broker is a one-process learning setup, not a production cluster or durable event store.

## Exercises

- Connect to `/ws`, send to `/app/jobs.update`, and subscribe to `/topic/jobs` using the separate Next.js client module.
- Replace the echo scaffold with server-owned job state; never trust client-submitted status/progress.
- Add authentication/authorization for destination subscriptions before exposing user-specific events; validate `Origin` and message size.
- Reconnect safely, show connection/error/offline states, unsubscribe/deactivate on component unmount, and fall back to polling if disconnected.
- Explain that a WebSocket event is a notification, not the durable source of truth. Refetch JOB-01 status from the HTTP API after reconnect.

## Verify

`mvn -DskipTests package` checks compilation. Use only localhost for the interactive exercise.

- [Spring WebSocket/STOMP reference](https://docs.spring.io/spring-framework/reference/web/websocket/stomp.html)
