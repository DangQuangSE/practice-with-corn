# Phase 02 — Spring REST and Next.js foundations

**Stories:** P1 paired backend/frontend practice; P1 standalone topics; P1 shared contract.
**Dependencies:** Phase 01.

## Deliverables

- Java exercises BE-01–BE-10 and BE-17–BE-18: HTTP/DTO basics; layering/DI; CRUD and 404/409; soft delete/restore; common success and Problem Details error contracts; request validation and body-size limits; database-backed list/search pagination; request correlation filter; CORS/security headers; profiles/secrets; OpenAPI.
- Frontend exercises FE-01–FE-07, FE-12, FE-13: routes/layouts, server/client boundary, fetch location, typed Java API client, async states, cache/revalidation, form validation/server errors, accessibility, secret boundary.
- Pair BE-01, BE-03–BE-06, BE-10 with FE tasks via small shared contracts. Keep BE-02, BE-07–BE-09 and FE-01/02/12/13 independently runnable when appropriate.
- Introduce TypeScript types, props, state, composition, and async-state handling within relevant FE briefs (not as unrelated setup tasks).
- Add minimal local checks for each module (Java tests/API checks; FE lint/type/component-level checks as the module supports them). Detailed reusable test-tool exercises remain in Phase 06.

## Acceptance criteria

- A learner can run a Java API and a Next.js module independently and compare each to a documented contract.
- FE handles loading/empty/error/success paths and displays backend validation errors without leaking server secrets.
- Error and success conventions are consistent, documented, and covered by observable checks.
- Search/filter work applies filtering in the database, bounds page sizes, and allowlists dynamic sort fields; malformed filters receive useful 4xx responses.
- BE-06 demonstrates date and status filters, explains the `Page` versus `Slice` choice, and verifies the selected pagination response contract.
- BE-18 tests a request below and above its configured body-size cap; oversized input returns HTTP 413 (or a documented container-specific equivalent) without stack-trace disclosure.
- Delete/restore behavior is explicit and independently tested; error responses do not expose stack traces.
- No Next.js Route Handler pass-through is added unless an exercise explicitly teaches BFF.

## Design Constraints

- Keep each exercise to one main concept and avoid requiring a database until the data phase; use in-memory fixtures/stubs for API fundamentals.
- Java owns domain/API behavior. Do not return JPA entities directly.
- Use official, pinned framework behavior when documenting fetch caching; do not assume every Next fetch is cached.
- CORS allowlists must be explicit; do not use wildcard origins with credentials.
- Request IDs may be logged, but credentials, passwords, access tokens, and refresh tokens must never be logged.

Preflight: Existing Java DSA uses a compact Vietnamese prompt, one visible TODO work area, and local examples; no Spring or Next.js app existed before this phase. Keep BE and FE as separate, minimal workspaces with explicit DTO/component boundaries and mockable API calls. Pin Spring Boot 4.1.1 with Java 21 source compatibility and Next.js 16.3.6; Next requires Node 20.9+, while Node 24 LTS is the recommended runtime. Local tooling currently has JDK 24, Maven 3.9.9, and Node 22.15.0; build checks will use installed tools and no live service credentials.

## Quality and Testing State

- Quality: skipped by user; no `ck:quality` gate was run.
- Testing: not started; unit tests were skipped by user. Maven package, TypeScript check, and Next.js production build were used as build/syntax checks only.
