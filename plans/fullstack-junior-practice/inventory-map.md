# Competency-to-exercise map

This is the proposed traceability map for spec inventory sections A–H. Difficulty: E = Easy, M = Medium, C = Challenge. Tier: Core or Extension. Shape: Pair = shared Java + Next.js brief/contract; Standalone = focused track task. A paired label does not mean either side depends on the other side's implementation.

## A. Java backend, HTTP, application structure

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| BE-01 | HTTP resource routes, methods, status codes, request/response DTOs | Core / E | Pair | 02 |
| BE-02 | Controller/service/repository roles and dependency injection | Core / E | Standalone | 02 |
| BE-03 | Small CRUD endpoint, not-found and duplicate/conflict behavior | Core / E | Pair | 02 |
| BE-04 | Common success/pagination envelope, RFC 9457-style errors, and no stack-trace disclosure | Core / M | Pair | 02 |
| BE-05 | Request validation and field-level error contract | Core / E | Pair | 02 |
| BE-06 | Database-side pagination/search, date/status filters, Page versus Slice, allowlisted sort/query fields | Core / M | Pair | 02 |
| BE-07 | Filter/interceptor, request ID, correlated logs without secrets or credentials | Extension / M | Standalone | 02 |
| BE-08 | Narrow CORS and security-header configuration | Core / M | Standalone | 02, 04 |
| BE-09 | Environment config, profiles, server-only secrets | Core / E | Standalone | 02, 06 |
| BE-10 | OpenAPI docs and local contract exploration | Core / E | Pair | 02 |
| BE-11 | JPA relationship cardinalities, association entity, fetch/cascade choices, DTO/entity boundary | Core / M | Pair | 03 |
| BE-12 | Transaction boundaries, rollback/isolation, multi-step update; keep remote calls outside long transactions | Core / M | Standalone | 03 |
| BE-13 | Recognize/fix N+1 with an appropriate fetch strategy; never serialize entities directly | Extension / M | Standalone | 03 |
| BE-14 | Race condition, optimistic locking, and optional pessimistic-lock comparison | Extension / C | Pair | 05 |
| BE-15 | Idempotent retryable operation | Extension / M | Pair | 05, 08 |
| BE-16 | Rate limiting and safe repeated-request handling | Extension / M | Standalone | 07 |
| BE-17 | Soft delete/restore semantics and authorization/error cases | Core / M | Standalone | 02 |
| BE-18 | Bound API request-body size and reject oversized requests with a controlled response | Core / M | Standalone | 02 |

## B. Next.js and frontend integration

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| FE-01 | App Router routes, layouts, params, nested pages | Core / E | Standalone | 02 |
| FE-02 | Server/Client Component boundary | Core / M | Standalone | 02 |
| FE-03 | Server-side versus browser-side fetching | Core / M | Pair | 02 |
| FE-04 | Typed API client aligned to Java contract | Core / E | Pair | 02 |
| FE-05 | Loading, empty, error, and not-found states | Core / E | Pair | 02 |
| FE-06 | Cache/revalidation and stale UI after mutation | Core / M | Pair | 02 |
| FE-07 | Form/schema validation and backend field-error display | Core / M | Pair | 02 |
| FE-08 | Session-aware routes and access states | Core / M | Pair | 04 |
| FE-09 | Search/filter/pagination/sort with URL query state | Core / M | Pair | 05 |
| FE-10 | Optimistic update and rollback on failure | Extension / M | Pair | 05 |
| FE-11 | Upload/download UX, progress, validation, retry | Extension / M | Pair | 07 |
| FE-12 | Accessible, keyboard-usable, responsive forms/data views | Core / M | Pair | 02, 05 |
| FE-13 | Keep server secrets out of browser bundles | Core / E | Standalone | 02, 06 |
| FE-14 | Display report aggregates and empty/error states | Core / M | Pair | 05 |
| FE-15 | CSV import feedback, partial/atomic result summary, and error-file download | Extension / M | Pair | 05 |
| FE-16 | Poll/display asynchronous job status and completion/failure states | Extension / M | Pair | 07 |

## C. Authentication, authorization, security

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| SEC-01 | Registration/login/logout/password change with Spring PasswordEncoder (BCrypt baseline) | Core / M | Pair | 04 |
| SEC-02 | JWT issuance/expiry/validation without hand-written crypto | Core / M | Standalone | 04 |
| SEC-03 | Role/permission checks and 401 versus 403 | Core / M | Pair | 04 |
| SEC-04 | Resource ownership checks across users | Core / M | Pair | 04 |
| SEC-05 | Safe browser session/token boundary and route behavior | Core / M | Pair | 04 |
| SEC-06 | Cookie, CSRF, CORS, XSS, and trust-boundary basics | Core / M | Pair | 04 |
| SEC-07 | Refresh rotation, revocation, expiry, logout | Extension / C | Pair | 04 |
| SEC-08 | Sensitive config/secret exposure | Core / E | Standalone | BE-09, OPS-01 (covered; no duplicate exercise) |
| SEC-09 | OAuth sign-in with mock/development provider | Extension / M | Pair | 07 |
| SEC-10 | Lock/unlock account state and deny login/access safely | Extension / M | Pair | 04 |
| TEST-04 | Authenticated, unauthenticated, forbidden security checks | Core / M | Standalone | 06 (also required by Phase 04 exit) |

## D. Common business workflows

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| FLOW-01 | Profile changes and duplicate-value conflicts | Core / E | Pair | 05 |
| FLOW-02 | Email verification/password reset with local mail sink | Extension / M | Pair | 07 |
| FLOW-03 | Catalog create/update/detail/search/filter | Core / M | Pair | 05 |
| FLOW-04 | Cart totals, discounts, coupon rules and boundaries | Core / M | Pair | 05 |
| FLOW-05 | Order creation and explicit state transitions | Core / M | Pair | 05 |
| FLOW-06 | Inventory reserve/release consistency and failed checkout | Core / C | Pair | 05 |
| PAY-01, PAY-02 | Checkout/payment request; pending/success/failure and return UI | Core / M | Pair | 08 |
| PAY-03 | Signature validation, replay protection, idempotent callback | Extension / C | Pair | 08 |
| PAY-04 | Cancellation, refund, and reconciliation states | Extension / C | Pair | 08 |
| FLOW-07 | Booking availability, overlap, timezone, cancellation | Extension / C | Pair | Deferred from Core; independent module in 05 |
| FLOW-08 | Subscription trial/renewal/failure/cancellation | Extension / C | Pair | Deferred from Core; optional after 08 |
| FLOW-09 | Role-checked approval/review transitions | Extension / M | Pair | 05 |
| FLOW-10 | Notification delivery and retry/failure handling | Extension / M | Pair | 07 |
| FLOW-11 | Audit actor/time/target and safe before/after summary; exclude secrets | Extension / M | Standalone | 05 |
| FLOW-12 | Idempotent notification lifecycle: template, persisted status/history, retry, duplicate suppression | Extension / M | Pair | 07 |
| FILE-01 | Safe bounded upload, content/type validation, generated name, metadata, replace/cleanup | Core / M | Standalone | 05 |
| DATA-01 | CSV import/export, row validation/duplicates, error report, partial versus atomic result | Extension / M | Pair | 05 |
| REPORT-01 | Database aggregate/projection API with date/status filters and a simple summary view | Core / M | Pair | 05 |
| JOB-01 | Scheduled task plus asynchronous job status lifecycle, locally verifiable | Extension / M | Pair | 07 |

## E. Third-party and infrastructure integrations

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| INT-01 | External REST client: mapping, timeouts, bounded retry, error translation | Extension / M | Standalone | 07 |
| PAY-01 | Provider-neutral fake payment lifecycle | Core / M | Pair | 08 |
| PAY-05 | PayOS payment-link adapter and callback verification | Extension / C | Pair | 08 |
| PAY-06 | VNPay request/return/IPN signature verification | Extension / C | Pair | 08 |
| FLOW-02 / FLOW-10 | Email adapter with local mail sink | Extension / M | Pair | 07 |
| INT-03 | Cloudinary signed-upload adapter and provider-error handling (after FILE-01) | Extension / M | Pair | 07 |
| SEC-09 | OAuth sign-in, mock/development mode | Extension / M | Pair | 07 |
| INT-04 | Redis cache-aside, TTL, invalidation; optional rate-limit reuse | Extension / M | Standalone | 07 |
| INT-05 | RabbitMQ producer/consumer, ack, retry/dead-letter basics | Extension / C | Standalone | 07 |
| INT-06 | Spring WebSocket/STOMP notifications with Next browser client | Extension / M | Pair | 07 |
| INT-07 | Generic inbound/outbound webhook contract and secret/signature handling | Extension / M | Standalone | 07 |
| INT-08 | Optional error-reporting/observability adapter; no production data | Extension / M | Standalone | 07 |

Supporting tools are introduced only in service of these objectives: Spring Security/JPA, Flyway, OpenAPI, Postman, JUnit/Mockito, Testcontainers, Vitest/React Testing Library, Playwright, Docker Compose, GitHub Actions, Mailpit (or equivalent local mail sink), and WireMock (or equivalent HTTP stub).

## F. Testing and verification

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| TEST-01 | Java unit tests for rules and service behavior | Core / E | Standalone | 06 |
| TEST-02 | Mock external dependencies and failure cases | Core / M | Standalone | 06 |
| TEST-03 | Controller/API status, contract shape, validation and errors | Core / M | Pair | 06 |
| TEST-04 | Auth allow/deny and ownership tests | Core / M | Standalone | 06 |
| TEST-05 | PostgreSQL persistence/transaction integration tests; Docker-optional path | Core / M | Standalone | 03, 06 |
| TEST-06 | Next.js/React component tests for states/interactions | Core / M | Standalone | 06 |
| TEST-07 | API contract check preventing FE/BE drift | Core / M | Pair | 06 |
| TEST-08 | Selected Playwright login/CRUD/fake-payment browser flows | Extension / C | Pair | 06, 08 |

## G. SQL practice — PostgreSQL

| ID | Competency and migration work | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| SQL-01 | Relational modeling, PK/FK/unique/check constraints | Core / E | Standalone | 03 |
| SQL-02 | SELECT, WHERE, ORDER BY, LIMIT/OFFSET, NULL | Core / E | Standalone | 03 |
| SQL-03 | INSERT/UPDATE/DELETE and consistency constraints | Core / E | Standalone | 03 |
| SQL-04 | INNER/LEFT JOIN and relationship queries | Core / M | Standalone | 03 |
| SQL-05 | GROUP BY, aggregates, HAVING | Core / M | Standalone | 03 |
| SQL-06 | Subqueries and CTEs | Core / M | Standalone | 03 |
| SQL-07 | Window functions and per-group ranking | Core / M | Standalone | 03 |
| SQL-08 | Transactions and basic isolation/concurrency behavior | Core / M | Standalone | 03 |
| SQL-09 | Index choice and PostgreSQL EXPLAIN (ANALYZE, BUFFERS) | Extension / C | Standalone | 03 |
| SQL-10 | Migrations/seeds/safe data corrections; adapt `sql_demo.sql` schema/data intent | Core / M | Standalone | 03 |
| SQL-11 | Offset versus keyset pagination | Extension / M | Standalone | 03 |
| SQL-12 | Convert useful `day1.sql` challenges; replace SQL Server-only plan/timing claims | Core / M | Standalone | 03 |

## H. DevOps and local operations

| ID | Competency | Tier / difficulty | Shape | Phase |
|---|---|---|---|---|
| OPS-01 | Environment files/profiles and secret-safe configuration | Core / E | Standalone | 06 |
| OPS-02 | Correct Java multi-stage Dockerfile and image run | Core / M | Standalone | 06 |
| OPS-03 | Next.js production build/container basics | Extension / M | Standalone | 06 |
| OPS-04 | Build context, image layers/cache, image-size tradeoffs | Extension / M | Standalone | 06 |
| OPS-05 | Compose ports, networks, env, service dependencies | Core / M | Standalone | 06 |
| OPS-06 | PostgreSQL volume and reset/seed workflow | Core / E | Standalone | 06 |
| OPS-07 | Health/readiness versus process start; service readiness checks | Core / M | Standalone | 06 |
| OPS-08 | Migration ordering and application startup behavior | Core / M | Standalone | 06 |
| OPS-09 | Local PostgreSQL/cache/mail/object-service dependencies | Extension / M | Standalone | 06 |
| OPS-10 | Reverse-proxy routes between frontend and backend | Extension / M | Pair | 06 |
| OPS-11 | CI lint/test/build workflow | Core / M | Standalone | 06 |
| OPS-12 | CI cache/artifacts and secret-safe configuration | Extension / M | Standalone | 06 |
| OPS-13 | Logs, container/network failure diagnosis, health reporting | Core / M | Standalone | 06 |
| OPS-14 | Optional local release configuration (no production access) | Extension / C | Standalone | Deferred from Core; optional after 06 |
