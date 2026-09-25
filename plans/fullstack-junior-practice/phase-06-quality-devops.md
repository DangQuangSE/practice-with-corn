# Phase 06 — Testing, Docker, CI, and local operations

**Stories:** P1 local verification and DevOps practice; P2 optional test tooling; P1 docs with commands/outcomes.
**Dependencies:** Phase 01; tests can be introduced alongside Phases 02–05, then consolidated here.

## Deliverables

- TEST-01–TEST-08: Java unit/service tests; mocks/failure cases; controller/API contract validation; auth deny/allow checks; PostgreSQL integration; FE component tests; contract drift checks; selected Playwright flows. These checks also cover new upload, CSV import, report, scheduled-job, and notification behaviors. Mark Testcontainers and Playwright setup as optional prerequisites with a simpler local alternative where reasonable.
- OPS-01–OPS-13: secret-safe config; Java multi-stage image; Next production build/container; build context/layers/cache; Compose ports/networks/dependencies; volume/reset/seed; health/readiness; migration startup ordering; local support services; reverse proxy; GitHub Actions lint/test/build; artifact/cache/secrets; logs/network/health diagnosis.
- Review existing `devops/docker/dockerFile` and `dockerCompose` as source examples. Convert issues into exercises: filename/build-context mismatch, Java `-jar` option ordering, fixed demo password, and readiness based on start-order alone. Preserve originals until replacement exercises are reviewed.
- Every DevOps task includes exact commands and an observable expected result/failure diagnosis. CI exercises never require production secrets.

## Acceptance criteria

- Testing inventory has one ID per listed level and has both success and failure-path examples.
- Compose services become healthy/ready before dependent checks; persistent state and reset instructions are explicit.
- CI is reproducible without paid services and reports lint/test/build failures clearly.
- All Docker and CI commands are checked against the versions pinned by the exercise when implementation is performed.

## Design Constraints

- Do not change production infrastructure or require cloud deployment.
- Never commit usable credentials. Examples use placeholders and an ignored local env file.
- Testcontainers/E2E must not make the Core curriculum unusable on a machine without Docker/browser automation.
- OPS-14 local release configuration remains an optional extension and cannot require production access.

## Quality and Testing State

- Quality: not evaluated
- Testing: not started
