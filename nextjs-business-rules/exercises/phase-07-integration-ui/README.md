# Phase 07 — Integration browser client

**IDs:** FE-11, FE-16, paired with INT-03, JOB-01, and INT-06. **Runtime:** Node 24.21.0, Next.js 16.3.6, TypeScript 5.9.3, `@stomp/stompjs` 7.3.0.

This client is independent of the provider and Java implementations. It uses STOMP.js (a maintained STOMP protocol client) and a public localhost URL only; never put Cloudinary/API secrets or bearer tokens into `NEXT_PUBLIC_` variables.

## Run and verify

```bash
npm ci
npm run dev
npm run typecheck
npm run build
```

Copy `.env.example` to `.env.local` only if you change local endpoint URLs. Run the Java [WebSocket module](../../../java-business-rules/exercises/phase-07-websocket/README.md) on port 8080 for the local event exercise. The upload endpoint remains a TODO until the signed-parameter exchange is implemented.

## Exercises

- **FE-11:** request short-lived signed parameters from Java; validate extension/size; display progress, rejection, cancellation, retry, and final server-verified status.
- **FE-16:** show connecting/connected/reconnecting/failed states; subscribe/unsubscribe cleanly; refetch durable HTTP job status after reconnect.
- Keep mock mode available if Java, Cloudinary, or a WebSocket service is unavailable.

- [STOMP.js official repository](https://github.com/stomp-js/stompjs)
