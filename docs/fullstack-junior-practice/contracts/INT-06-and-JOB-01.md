# INT-06 / JOB-01 / FE-16 — Realtime job status

- Java hosts STOMP over `/ws`; Next.js is the browser client. Local destination: subscribe `/topic/jobs`; server application prefix `/app`.
- Event DTO: `{ jobId, status, completedItems, occurredAt }`. It contains no secret, personal data, or arbitrary client-authored state.
- HTTP `GET /api/jobs/{jobId}` is authoritative; WebSocket is best-effort notification only. Refetch after connect/reconnect and show a polling fallback.
- Use authenticated ownership checks for subscriptions before production use. Validate origins, destination authorization, payload size, connection lifecycle, and reconnect limits.
- Run Java and Next workspaces independently; mock events remain available when WebSocket is offline.
