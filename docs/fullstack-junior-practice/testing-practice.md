# Testing practice — TEST-01–TEST-08

These are exercises, not a request to generate or run tests now. Add tests when you choose to study each item. Prefer small deterministic cases; never rely on sleeps, live providers, real credentials, or production data.

## TEST-01 — Java unit tests for rules and services (Core / Easy)

Pick a pure policy from Phase 05. List its invariant, valid boundary, invalid boundary, and expected error. Practice JUnit 5 assertions and descriptive test names without Spring context. Consider `BigDecimal` scale/rounding and state transition tables.

## TEST-02 — Mocks and failure paths (Core / Medium)

For a service that calls a repository/provider, test success, dependency failure, timeout/retry decision, and duplicate response. Mock only the external boundary; keep domain rules real. Assert safe client-visible behavior and that secrets/payloads are not logged.

## TEST-03 — Controller/API contract (Core / Medium, Pair)

Use Spring MVC slice testing to exercise status, validation, RFC 9457 errors, envelope, headers, and DTO fields. Verify a successful request and malformed/duplicate/not-found paths. Compare the result with a shared contract without starting PostgreSQL.

## TEST-04 — Authentication, authorization, ownership (Core / Medium)

Build a deny/allow matrix: anonymous, authenticated owner, authenticated non-owner, insufficient scope, authorized admin. Distinguish 401 from 403 and test direct API access—not just hidden UI.

## TEST-05 — PostgreSQL persistence/transaction (Core / Medium)

Test migration, constraints, repository projection, and rollback using Testcontainers when Docker is available. Without Docker, run against the local PostgreSQL setup from Phase 03 and reset only its dedicated fixture schema. Prove failed checkout leaves no partial order/inventory change.

## TEST-06 — Next.js component states (Core / Medium)

For a form/table, cover loading, empty, success, validation, server error, keyboard use, and optimistic rollback. Mock the Java API boundary; assert accessible role/name rather than CSS selectors.

## TEST-07 — Contract drift (Core / Medium, Pair)

Choose an OpenAPI document as contract source. Validate backend response examples and frontend DTO/client expectations against it. Add a deliberate breaking field/status change and observe that the check fails before integration.

## TEST-08 — Browser E2E (Extension / Challenge)

Use Playwright for one login/CRUD flow and, later, the fake-payment lifecycle only. Keep user data synthetic, services local, and the scenario deterministic. Cover a negative path and verify UI state after server rejection. Avoid live provider or production credentials.

## Suggested progression and evidence

1. State the behavior and test layer that owns it.
2. Keep pure rules at unit level, HTTP mapping at controller/API level, and schema/transaction behavior at database integration level.
3. Record command, fixture/reset procedure, expected failure, and what the test does not prove.
4. Run locally before adding the relevant CI command; Docker/browser-dependent extensions must have a documented prerequisite and non-Docker alternative where practical.

This curriculum pass intentionally did not author or execute test files, per user choice.
