# Phase 05 — Common business workflows

**Stories:** P1 practical paired workflows; P1 independently understandable tasks; P3 capstone excluded.
**Dependencies:** Phases 02–04.

## Deliverables

- Core paired workflows FLOW-01, FLOW-03–FLOW-06: profile/conflict handling; catalog CRUD/search; cart/discount boundaries; order transitions; inventory reserve/release and failed-checkout consistency.
- FILE-01: bounded generic upload with allowlisted content/type, size limits, server-generated storage name, metadata, replacement and cleanup; the provider adapter remains in Phase 07.
- REPORT-01 with FE-14: a filtered aggregate/projection endpoint and compact summary view; basic report practice is Core, while caching and complex analytics are not.
- DATA-01 with FE-15: bounded CSV import/export, row validation, duplicate handling, partial-versus-atomic outcome and downloadable error report; Extension only.
- Extension modules FLOW-07–FLOW-09 and FLOW-11: booking/time zones/overlap/cancel; subscription state lifecycle; role-checked approval; actor/time/target audit with a safe before/after summary. Each gets its own small fixture/brief rather than extending a shared application.
- BE-14–BE-15 for optimistic locking and idempotent retry; introduce only when the workflow demonstrates a real conflict/retry requirement.
- FE-09–FE-10 for URL-driven query state and optimistic update/rollback; FE-12 accessibility applied to selected forms and tables.
- Each pair defines input/output/state transitions and error cases before coding. Provide mock API mode so FE and BE can be practiced independently.

## Acceptance criteria

- Core workflow exercises have explicit invariants and boundary cases (duplicate profile data, invalid coupon, illegal state transition, failed reservation, retry).
- FILE-01 rejects disallowed/oversized input, never uses a user-supplied path as the storage name, and tests replacement/cleanup outcomes.
- REPORT-01 filters and aggregates in PostgreSQL, returns a narrow DTO, and covers empty/date/status boundaries.
- DATA-01 documents whether each import is partial or atomic, has bounded row/file limits, and returns row-level errors without leaking server details.
- FLOW-11 records actor, timestamp, target, and a safe before/after summary; tests verify sensitive values such as passwords and tokens are excluded.
- State changes are validated on the Java side and reflected in the typed FE contract.
- Optimistic locking/idempotency challenges demonstrate duplicate or concurrent behavior with deterministic tests, not timing assumptions.
- Booking/subscription/approval/audit modules are clearly labeled Extensions and do not block Core completion.

## Design Constraints

- No single app or shared database state is required across all workflows; use isolated schemas/fixtures or independently resettable modules.
- Do not implement real payment here; payment lifecycle belongs to Phase 08 and begins with a fake provider.
- Do not claim exactly-once behavior for retries or distributed workflows.
- File fixtures are harmless, size-bounded, and locally resettable; do not require Cloudinary credentials in FILE-01.

Preflight: Phase 03 established the shared PostgreSQL schema, deterministic synthetic fixtures, and DTO/projection pattern; Phase 04 keeps browser auth and bearer auth separate. Each Phase 05 workspace will own its state/fixture, use BigDecimal and explicit state transitions, and keep upload/import limits bounded. Paired Next.js tasks use typed DTOs and mock responses; no workflow depends on the Phase 02 starter being implemented.

## Quality and Testing State

- Quality: skipped by user for this implementation pass
- Testing: unit tests skipped by user; Maven package and Next.js typecheck/build passed
