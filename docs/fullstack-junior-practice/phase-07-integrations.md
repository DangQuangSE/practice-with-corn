# Phase 07 — Service and realtime integrations

Learn adapters one at a time with local fakes. The modules have independent Maven/Next workspaces; no single Compose stack or vendor account is required.

## Backend modules

- [INT-01, FLOW-02/FLOW-10/FLOW-12, JOB-01, SEC-09, INT-07/08](../../java-business-rules/exercises/phase-07-service-boundaries/README.md)
- [INT-04 cache-aside and BE-16 rate limits (Redis)](../../java-business-rules/exercises/phase-07-redis-cache/README.md)
- [INT-05 publisher/consumer, ack, retry, dead letter (RabbitMQ)](../../java-business-rules/exercises/phase-07-rabbitmq/README.md)
- [INT-03 signed upload adapter (Cloudinary)](../../java-business-rules/exercises/phase-07-cloudinary-upload/README.md)
- [INT-06 server-hosted WebSocket/STOMP](../../java-business-rules/exercises/phase-07-websocket/README.md)

## Frontend and contracts

- [FE-11 upload progress/errors and FE-16 realtime job status](../../nextjs-business-rules/exercises/phase-07-integration-ui/README.md)
- [HTTP, email, notifications, and job contract](contracts/INT-01-and-notification-jobs.md)
- [Cloudinary signed-upload contract](contracts/INT-03-signed-upload.md)
- [Redis and RabbitMQ contracts](contracts/INT-04-and-INT-05.md)
- [WebSocket/job contract](contracts/INT-06-and-JOB-01.md)
- [OAuth, webhook, observability, email/audit boundaries](contracts/SEC-09-and-observability.md)

## Safety and local setup

Start Redis, PostgreSQL, and Mailpit individually from the [Phase 06 support stack](../../devops/exercises/phase-06-operations/README.md). RabbitMQ has an [isolated local Compose file](../../java-business-rules/exercises/phase-07-rabbitmq/README.md). Cloudinary uses a fake by default; real credentials are optional and server-side only.

- [Spring scheduling](https://docs.spring.io/spring-framework/reference/integration/scheduling.html)
- [Spring Redis](https://docs.spring.io/spring-data/redis/reference/redis/redis-cache.html)
- [RabbitMQ confirms and acknowledgements](https://www.rabbitmq.com/docs/confirms)
- [Spring WebSocket/STOMP](https://docs.spring.io/spring-framework/reference/web/websocket/stomp.html)
- [STOMP.js client](https://github.com/stomp-js/stompjs)
