# Spec: Junior Fullstack Practice Exercises

**Date:** 2026-09-26
**Status:** Ready for planning

---

## Problem Statement

The repository's DSA practice is focused and code-first, but it does not cover the breadth of web/backend/frontend competencies expected in junior full-stack interviews. The learner wants an open-ended inventory of small Java backend, Next.js frontend, SQL, DevOps, and selected integration exercises, with paired Java + Next.js tasks sharing a context when that makes sense, without building one large application.

---

## User Stories

- **[P1]** As a learner, I want a broad inventory of junior full-stack interview competencies before an implementation plan is written.
  Accepted when: each core competency is mapped to one or more candidate exercises, and there is no arbitrary 10-exercise cap or forced 3-Easy/7-Medium ratio.

- **[P1]** As a learner, I want related Java backend and Next.js frontend exercises to share one problem and API contract so I can practice both sides in the same context.
  Accepted when: every paired exercise has a shared brief/API contract, a Java backend work area, a Next.js/TypeScript frontend work area, and a local verification method for each side.

- **[P1]** As a learner, I want standalone practice for backend-, frontend-, SQL-, and DevOps-specific topics when pairing them would add unnecessary scope.
  Accepted when: each item in the final inventory is marked paired or standalone and remains independently understandable.

- **[P1]** As a learner, I want docs to explain concepts and link directly to exercise modules.
  Accepted when: the learning index maps theory topics to the corresponding exercise IDs and folders.

- **[P2]** As a learner, I want optional hands-on integrations with common third-party services/tools.
  Accepted when: external-service exercises can be completed with local fakes, emulators, or sandbox modes and do not require live credentials or paid accounts.

- **[P3]** A complete product application can be used later as an optional capstone; it is outside the initial exercise curriculum.

---

## Functional Requirements

1. FR-01: Organize the curriculum into Java backend + Next.js/TypeScript frontend practice, `sql-practice`, and `devops`. Exclude `DE_PRACTICE` from the learning path. Keep docs as the theory and navigation layer.
2. FR-02: Do not impose a fixed total exercise count or a fixed difficulty ratio. Determine the count from the approved competency inventory; split complex topics into multiple focused exercises where useful.
3. FR-03: Every exercise is independently understandable, has one primary learning objective, a TODO/work area, acceptance criteria, and a local verification method. Exercises must not require completion of a growing capstone application.
4. FR-04: Pair Java backend and Next.js frontend work around one shared brief/API contract when the competency is end-to-end. Backend-only and frontend-only topics may remain standalone.
5. FR-05: The Java/backend inventory uses Spring Boot and covers HTTP/REST, layered API design, DTO mapping, common response/error handling, validation, configuration, filters/interceptors, CORS, persistence, transactions/concurrency, security, safe file handling, import/export, scheduled/asynchronous work, reporting, integrations, and backend testing.
6. FR-06: The Next.js/frontend inventory covers App Router, server/client boundaries, API clients, data fetching/caching, route/session access, forms, loading/error states, optimistic updates, accessibility, report/job/import result views, file handling, and frontend testing.
7. FR-07: Include common interview business flows such as account registration/recovery/profile changes, CRUD and state changes (including soft delete/restore), catalog/search, pricing/discounts, order lifecycle, inventory reservation, payment/status/refund, safe file handling, import/export, reports, scheduled/asynchronous work, notifications, and audit history. Pair frontend/backend portions where useful.
8. FR-08: Include practical integration topics requested by the learner: Redis, RabbitMQ, Cloudinary, WebSocket, and payment integrations for PayOS and VNPay, along with suitable junior-level additions such as email, OAuth, external REST APIs, OpenAPI, scheduled tasks, and test tooling. Start provider flows with local fakes/mocks; make vendor sandbox exercises optional and never require live payment or production credentials.
9. FR-09: Include testing at appropriate levels: backend unit/service/controller/security and database integration tests; frontend unit/component tests; API contract checks; import validation/transaction-boundary checks; and selected browser end-to-end flows.
10. FR-10: SQL exercises use one declared dialect and consistent schema, progressing through data modeling, filtering, joins, aggregation, subqueries/CTEs, window functions, transactions, indexes, and query plans.
11. FR-11: DevOps exercises cover local configuration/secrets, Dockerfiles, Docker Compose, networking/volumes, readiness/health, migrations, logs/debugging, and CI checks. Each task states the command and expected result.
12. FR-12: Every inventory competency is assigned an exercise ID or explicitly deferred with a reason before the plan is approved.
13. FR-13: Exercise materials provide scaffolding and checks, not complete answer implementations. Reuse the repository's focused, learner-oriented exercise style.
14. FR-14: Preserve the existing DSA track and Java interview theory as references; avoid duplicating their existing exercise sets.
15. FR-15: Resolve the existing T-SQL versus PostgreSQL mismatch before adding runnable SQL exercises. Adapt useful content from both `sql-practice/day1.sql` and `sql-practice/sql_demo.sql`; preserve challenge intent while replacing SQL Server-only syntax and performance claims with PostgreSQL-appropriate versions.

---

## Draft Curriculum Inventory

This is a competency inventory to review before `/ck:plan`, not a fixed exercise count. A line can become one exercise or be split if it combines distinct interview skills. Mark each approved item as paired (Java + Next.js) or standalone during planning.

During review, tag each item `Core` (baseline junior expectation) or `Extension` (useful deeper practice or optional integration). Likely Core areas include HTTP/API, CRUD/data, validation/errors, auth basics, frontend data/forms, SQL fundamentals, automated tests, and local Docker/CI. Likely Extensions include payment providers, OAuth, queues, caching, concurrency controls, and deeper observability. These tags are proposals, not final assignments.

### A. Java backend, HTTP, and application structure

- HTTP/REST basics: resource routes, methods, status codes, request/response DTOs.
- Controller/service/repository responsibilities and dependency injection.
- Small CRUD endpoint with not-found and duplicate/conflict behavior; soft delete and restore as a focused follow-up.
- Consistent API response envelope and global exception mapping.
- Request validation and useful field-level error responses.
- Pagination, sorting, filtering, and search API contracts, including date/status filters, Page versus Slice, database-side filtering, and allowlisted sort fields.
- Filters/interceptors, request IDs, and correlation-aware logs without secrets or credentials.
- CORS and security-header configuration.
- Environment-based configuration, profiles, and secret handling.
- OpenAPI/Swagger documentation and trying an API contract locally.
- Persistence mapping for common relationship cardinalities and association entities with their own attributes; avoid leaking entities into API responses.
- Transactions, rollback, isolation boundaries, and multi-step updates; keep external API calls outside long-running database transactions.
- Avoiding N+1 queries and recognizing basic query-performance issues.
- Concurrent updates, race conditions, optimistic locking, and an optional introductory pessimistic-locking comparison.
- Idempotent operations for retryable requests.
- Rate limiting and safe handling of repeated/abusive requests.

### B. Next.js and frontend integration

- App Router routes, layouts, route parameters, and nested pages.
- Server Components versus Client Components and choosing the boundary.
- Server-side versus browser-side data fetching.
- Typed API client matching the Java request/response contract.
- Loading, empty, error, and not-found states.
- Data caching, revalidation, and avoiding stale UI after mutations.
- Form submission, schema validation, and displaying backend field errors.
- Session-aware routes and frontend access states.
- Search, filters, pagination, sorting, and URL query state.
- Optimistic updates with rollback on failure.
- File upload/download UX, progress, validation errors, and retry.
- Accessible, keyboard-usable, responsive forms and data views.
- Keeping server-only secrets out of browser bundles.
- Import-result and report/job-status views where paired exercises define those contracts.

### C. Authentication, authorization, and security

- Registration and safe password hashing through Spring Security `PasswordEncoder` (use BCrypt for the focused baseline exercise).
- Login/logout, password change, account lock/unlock behavior, and JWT issuance/verification/expiry.
- Refresh-token rotation, revocation, expiry, and logout behavior.
- Role/permission checks (401 versus 403).
- Resource ownership checks (a user cannot access another user's record).
- Secure browser session/token handling and route access behavior.
- Cookies, CSRF, CORS, XSS, and security boundaries at a junior-appropriate level.
- Security-sensitive configuration and preventing secret exposure.
- Avoiding stack-trace disclosure, sensitive credential logging, unsafe dynamic SQL, and unbounded request/file sizes.
- Security tests for authenticated, unauthenticated, and forbidden requests.

### D. Common business workflows

- Account profile update and duplicate-value conflicts.
- Email verification and password-reset flow using a local mail sink.
- Account recovery and administrative account-state changes; keep these as isolated auth exercises, not a full identity product.
- Soft-delete/restore behavior with explicit not-found and authorization cases.
- Catalog create/update/detail and search/filter.
- Cart totals, discounts, coupon rules, and boundary cases.
- Order creation and explicit order-state transitions.
- Inventory reserve/release and consistency under failed checkout.
- Safe upload of a bounded file with content/type validation, generated storage name, metadata, replacement, and cleanup behavior.
- CSV import/export with malformed/duplicate rows, an error report, and an explicit all-or-nothing versus partial-success contract.
- Basic report endpoint using database aggregation/projection and date/status filters; advanced dashboarding/caching remains optional.
- Checkout/payment creation and redirect/return handling.
- Payment pending/success/failure UI and backend state mapping.
- Webhook signature verification, replay protection, and idempotent processing.
- Cancellation, refund, and payment/order reconciliation states.
- Booking/reservation availability, overlapping time slots, timezone boundaries, and cancellation.
- Subscription lifecycle such as trial, renewal, failed payment, and cancellation.
- Approval/review workflow with role-checked state transitions.
- Notification lifecycle: email/in-system delivery, template rendering, persisted status/history, retry, and duplicate suppression.
- Scheduled task and asynchronous job lifecycle with observable status; use a simple local task first and a queue only in the RabbitMQ extension.
- Audit history for important state changes, including actor, time, target, and a safe before/after summary.

### E. Third-party and infrastructure integrations

- External REST client: request mapping, timeouts, retries, and provider-error translation.
- Provider-neutral payment lifecycle using a local fake before any vendor-specific exercise.
- PayOS payment-link/request lifecycle and callback/webhook verification as an optional provider-specific adapter exercise.
- VNPay payment request/return and IPN verification as an optional provider-specific adapter exercise.
- Email provider adapter using a local mail sink or sandbox.
- Cloudinary upload flow, signed-upload boundary, and provider-error handling; retain a local fake/stubbed mode for automated practice.
- Generic safe upload validation/storage lifecycle before the optional Cloudinary adapter.
- OAuth sign-in using a mock or development sandbox.
- Redis caching, expiration, invalidation, and optionally rate limiting for a small read flow.
- RabbitMQ producer/consumer flow for a notification or other retryable operation, including acknowledgements and retry/dead-letter behavior at an introductory level.
- Spring scheduled tasks and local asynchronous-job status; do not require a distributed scheduler or production queue for the basic exercise.
- WebSocket notifications or status updates, paired across Spring Boot and a Next.js client; keep this distinct from third-party provider integrations.
- Inbound/outbound webhook contract and safe secret/signature configuration.
- Optional error reporting/observability integration without sending production data.

Additional junior-appropriate tools to consider include Spring Security, JPA/Hibernate, Flyway, Swagger/OpenAPI, Postman, JUnit/Mockito, Testcontainers, React Testing Library/Vitest, Playwright, Docker Compose, GitHub Actions, a local email sink such as Mailpit, and an HTTP stub such as WireMock. Introduce them only when they support a listed competency, not as unrelated setup exercises.

Recommended level split: Core covers HTTP/REST, Spring Boot layering, validation/errors, PostgreSQL/JPA, Next.js API/forms/rendering basics, auth fundamentals, safe bounded file handling, a basic aggregate report, unit/API tests, and local Docker Compose/CI. Extensions include CSV bulk processing, account lock controls, refresh rotation, scheduled/asynchronous jobs, Redis, RabbitMQ, WebSocket/STOMP, Cloudinary, OAuth, and provider-specific PayOS/VNPay flows. The plan assigns prerequisites and difficulty; not every extension is required before the Core checkpoint.

### F. Testing and verification

- Java unit tests for pure rules and service behavior.
- Mock-based tests for external dependencies and failure cases.
- Controller/API tests for status, response shape, validation, and errors.
- Authentication/authorization tests for allow/deny cases.
- Database integration tests for persistence and transactions.
- Next.js/React unit or component tests for forms, states, and interactions.
- API-contract checks that keep frontend assumptions aligned with backend responses.
- Browser end-to-end tests for selected login, CRUD, and payment-sandbox flows.
- Deterministic tests for CSV row validation/atomicity, scheduled-job outcomes, report filters, upload limits, and notification deduplication as applicable to each module.

### G. SQL practice

- Relational schema design, primary/foreign keys, unique/check constraints.
- SELECT, WHERE, ORDER BY, LIMIT/OFFSET, and null handling.
- INSERT/UPDATE/DELETE with consistency constraints.
- Inner/left joins and relationship queries.
- GROUP BY, aggregate functions, and HAVING.
- Subqueries and common table expressions.
- Window functions for ranking and per-group calculations.
- Transactions and basic isolation/concurrency behavior.
- Index selection and reading a basic query plan.
- Schema migration/seed data and safe data corrections.
- Offset versus keyset pagination at an introductory level.

### H. DevOps and local operations

- Environment files, profiles, and secret-safe configuration.
- Java backend Dockerfile and multi-stage image basics.
- Next.js production build/container basics.
- Build context, layers, cache, and image-size tradeoffs.
- Docker Compose ports, networks, environment, and service dependencies.
- Persistent database volumes and local reset/seed workflow.
- Health checks/readiness versus mere process startup.
- Database migration ordering and application startup behavior.
- Local supporting services (for example database, cache, mail sink, or object-storage emulator).
- Reverse proxy basics and routing to frontend/backend.
- CI workflow for lint, test, and build.
- CI cache/artifact handling and secret-safe configuration.
- Inspecting logs, diagnosing container/network failures, and reporting health.
- Local release/deployment configuration as an optional extension, never requiring production access.

---

## Non-Functional Requirements

- **Scope:** Each exercise has one primary objective and can be solved without building the full product. Complex topics are split into smaller modules.
- **Security:** No exercise requires live credentials, real customer data, production access, or live payment. Prefer local fakes, emulators, or sandbox modes.
- **File handling:** Upload exercises document and test size/type limits, generated storage names, safe metadata, and cleanup; use harmless local fixtures.
- **Availability:** Core Java, Next.js, and SQL exercises can be verified locally. Docker prerequisites and any optional external sandbox requirements are documented.
- **Maintainability:** Every exercise has an ID, title, competency, difficulty/extension label, TODO area, acceptance criteria, and verification instructions.

---

## Success Criteria

- [ ] 100% of the approved core competencies in the inventory map to at least one exercise ID; every omitted item has an explicit deferral reason.
- [ ] The final exercise count is derived from the mapped inventory; no approved core competency is dropped solely to meet a fixed count or difficulty ratio.
- [ ] Difficulty and optional Challenge status are assigned per exercise, without a forced Easy/Medium ratio.
- [ ] Every paired exercise has one shared brief/API contract and independently verifiable Java backend and Next.js frontend work areas.
- [ ] Every exercise is small and independently runnable/understandable; no task requires completion of a large application.
- [ ] Every third-party integration has a local fake/emulator or clearly documented sandbox option and requires zero live secrets.
- [ ] All SQL exercises use the selected dialect and same documented schema.
- [ ] Every DevOps exercise has a command and observable expected outcome.
- [ ] The docs index links to each track, theory guide, and exercise inventory.

---

## Out of Scope

- One complete production-ready full-stack product or a sequence that can only be completed as a monolithic application.
- Production hosting, real credentials, production data, or required paid external services.
- A separate `DE_PRACTICE` curriculum in this scope.
- Full answer implementations inside exercise files.
- Recreating existing DSA or Java interview question sets.
- Deep-dive requirements for Kubernetes, event sourcing, CQRS, Saga, distributed transactions, reactive systems, and complex microservices; these may be future topics but are not part of this junior curriculum.

---

## Assumptions

- The target is a junior full-stack role using Java on the backend and Next.js/TypeScript on the frontend.
- Spring Boot is the selected Java backend framework.
- Full-stack work is paired around a shared API/context only where useful; SQL, DevOps, and narrow framework skills can be standalone.
- The learner specifically requested Redis, RabbitMQ, Cloudinary, WebSocket, PayOS, and VNPay integrations; appropriate adjacent tools can be proposed if they fit junior interview practice.
- Third-party services should use fakes, local emulators, or sandbox mode first.
- The existing DSA and Java interview material remains the source for algorithm/data-structure interview practice.
- PostgreSQL is the selected SQL dialect; useful content from both `sql-practice/day1.sql` and `sql-practice/sql_demo.sql` should be adapted deliberately.
- PayOS and VNPay are requested provider-specific topics, but no live credentials or real transactions are in scope. Payment behavior must remain fully testable against a local fake; any manual provider sandbox walkthrough is optional.
- The curriculum inventory is broad but cannot guarantee every company-specific interview question; core versus optional topics will be made explicit.

---
