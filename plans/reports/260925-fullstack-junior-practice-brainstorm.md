# Brainstorm: Junior Fullstack Practice Exercises

**Created:** 2026-09-25
**Updated:** 2026-09-26

## Ideas Explored

- Preserve the DSA style: small, code-first exercises with a clear goal, TODO work area, and a way to check the result; avoid one large application.
- Pair Java backend and Next.js/TypeScript frontend around a shared brief/API contract when the topic naturally spans both layers. Backend-only, frontend-only, SQL, and DevOps competencies can remain focused standalone exercises.
- Do not cap the curriculum at 10 tasks or force a 3-Easy/7-Medium split. Build the inventory from junior interview coverage; split a complex competency into multiple exercises or group closely related basics where appropriate.
- Cover web/backend foundations, frontend/Next.js, data/SQL, common business workflows, auth/security, testing, operations, and selected third-party integrations.
- Keep third-party integrations optional and local-first: use fakes, local emulators, or sandbox modes; do not require live credentials, paid plans, or production accounts.
- Keep `docs` as the theory/topic map, retain `sql-practice`, and exclude `DE_PRACTICE` from the learning path.

## User's Direction

The user wants a sufficiently broad set of small exercises for Junior Fullstack interview preparation, with no arbitrary maximum task count. Java/Spring Boot is the backend and Next.js is the frontend, paired in the same context where useful. The competency list should be established before planning. Requested integration topics now explicitly include Redis, RabbitMQ, Cloudinary, WebSocket, PayOS, and VNPay, plus suitable adjacent tools for junior practice.

## Proposed Curriculum Inventory

The detailed candidate inventory is in `plans/fullstack-junior-practice/spec.md`. It covers:

- Java/Spring backend and HTTP/API design.
- Next.js/TypeScript routing, rendering, API consumption, forms, state, and UI behavior.
- Persistence, SQL, transactions, and performance basics.
- Authentication, authorization, web security, and session handling.
- Common business flows such as accounts, catalog, orders, inventory, payment, refunds, and notifications.
- Testing at unit, API, database, component, and end-to-end levels.
- Docker/Compose, environment configuration, CI, health, logs, and debugging.
- Integrations requested by the user: Redis, RabbitMQ, Cloudinary, WebSocket, PayOS, and VNPay. PayOS/VNPay should have a provider-neutral mock flow first, then optional provider-specific sandbox adapters.
- Additional junior-appropriate tooling such as Spring Security, JPA/Hibernate, Flyway, Swagger/OpenAPI, JUnit/Mockito, Testcontainers, Playwright, Docker Compose, GitHub Actions, and a local email sink, introduced only to practice a specific competency.

This is a coverage inventory, not a fixed task count or a promise to enumerate every possible interview question. Each approved competency should map to at least one exercise or be explicitly deferred. The later plan can divide complex competencies into multiple small exercises and label them Easy, Medium, or optional Challenge.

## Decisions for Planning

- Use Spring Boot for Java backend exercises and PostgreSQL for SQL practice; adapt useful existing T-SQL content deliberately.
- No maximum exercise count or forced difficulty ratio; map the approved competency inventory and split topics into small standalone exercises.
- Keep provider-specific payment credentials optional. Start with a mock contract, then document PayOS/VNPay sandbox steps only if they can be exercised safely without live transactions.
- Distinguish infrastructure/protocol topics (Redis, RabbitMQ, WebSocket) from third-party SaaS/API integrations (Cloudinary, PayOS, VNPay, email/OAuth providers).

## Risks

- “Everything that may appear in an interview” is unbounded; use a reviewed competency inventory with core versus optional-extension labels rather than claiming universal coverage.
- Pairing too many backend and frontend tasks can turn a practice folder into a large app; keep each exercise independent with a narrow shared contract.
- Authentication, payment, and external-service topics can expose secrets or depend on accounts; use local fakes/sandboxes and never require real credentials.
- The current SQL exercise uses SQL Server syntax while the chosen practice dialect is PostgreSQL; adapt useful questions and label PostgreSQL syntax consistently.
