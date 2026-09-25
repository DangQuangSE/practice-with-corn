# Junior Fullstack Practice Curriculum Plan

**Status:** Draft for learner review
**Planning mode:** Hard
**Test mode:** Default (no TDD flag requested)
**Spec:** [spec.md](spec.md)
**Inventory map:** [inventory-map.md](inventory-map.md)

## Scope challenge and planning decisions

- **Exists:** DSA and Java interview practice already exist. The Java/Next.js business-practice folders are empty; SQL has two SQL Server-oriented scripts; Docker examples are drafts. Preserve existing learning tracks and `.gitignore` changes.
- **Minimum:** Add an inventory-backed set of small, independently understandable practice modules, shared contracts only for genuinely end-to-end exercises, and a docs index. This plan does not build a product or implement the curriculum.
- **Complexity:** Hard: multiple tracks, authentication/payment boundaries, database migration, and third-party integrations.
- **Spec quality:** PASS at planning time: no unresolved clarification marker; P1/P2/P3 stories and measurable success criteria exist.
- **Count and level:** No fixed exercise count or difficulty ratio. Every mapped item has a proposed ID, Core/Extension tier, paired/standalone shape, difficulty, and phase. Keep Extension work optional after the Core checkpoint.

### Repository shape proposed for implementation

- `java-business-rules/`: focused Spring Boot backend exercises; avoid one accumulating application.
- `nextjs-business-rules/`: focused Next.js App Router + TypeScript exercises.
- `docs/fullstack-junior-practice/`: index, API-contract conventions, concept guides, and links to exercise IDs.
- `sql-practice/`: PostgreSQL dialect/setup guide, shared schema/seed, challenges, and adapted source material.
- `devops/`: runnable Docker/Compose/CI/debugging exercises. Retain other existing folders and do not create a `DE_PRACTICE` track.
- Paired exercises get a short shared brief/API contract in docs and separate BE/FE work areas. The contract is the coordination point, not a shared growing codebase. Standalone exercises remain independently runnable.

### Conventions and decisions

1. Mark every exercise `Core` or `Extension` and `Easy`, `Medium`, or `Challenge`; difficulty describes that exercise, not a quota. Give each one ID, objective, brief, scaffold/TODO, acceptance criteria, and local verification steps. Include hints separately from solutions; do not ship full answers.
2. Java owns domain APIs. Next.js calls Java directly for paired practice; do not add a pass-through BFF by default. Add BFF only as a separate extension exercise with its own objective.
3. Establish one documented success-response convention (typed success envelope, including pagination metadata where useful) and RFC 9457-style error responses. Test mapping and validation details; do not expose persistence entities.
4. Use PostgreSQL for all new SQL work, with one documented schema. Inventory and adapt useful content in both `day1.sql` and `sql_demo.sql`; preserve intent but replace SQL Server syntax, SQL Server plan terminology, and unsupported fixed timing promises. Performance work uses a controlled dataset and PostgreSQL `EXPLAIN (ANALYZE, BUFFERS)`; large seed generation is optional Challenge work.
5. Java owns authentication and domain APIs; Next.js calls Java directly by default and a BFF is a separate comparison exercise. Keep the paired browser session contract explicit: Java-owned HttpOnly/Secure/SameSite session cookie with CSRF protection; separately practice Bearer JWT issuance/validation through Spring Security and maintained libraries. Do not hand-write JWT filters/cryptography or put credentials in local/session storage. Backend authorization remains authoritative; refresh rotation and OAuth are Extensions.
6. External integrations are adapter exercises with local fakes/stubs first. Never require production keys, real customer data, paid accounts, or live money movement. WebSocket is hosted by Spring; Next.js is a browser client, not a presumed persistent socket server.
7. Put lightweight tests/checks with exercises as they are introduced. Add reusable test-tool and CI exercises later. Testcontainers and browser E2E are optional prerequisites when Docker/browser setup is unavailable; provide a mock/local alternative for Core verification.
8. Include the remaining generic junior-backend topics: safe file validation before Cloudinary, CSV import/export, scheduled/asynchronous job status, basic reports, detailed audit fields, soft delete/restore, and account lifecycle controls.

## Phases

| Phase | Outcome | Stories | Dependencies |
|---|---|---|---|
| [01 — Curriculum structure and authoring rules](phase-01-structure.md) | Stable ID, folder, brief/contract, scaffold, and documentation conventions; inventory locked before authoring | P1 inventory, P1 focused exercises, P1 docs, P3 out-of-scope boundary | None |
| [02 — Spring REST and Next.js foundations](phase-02-foundations.md) | First paired and standalone HTTP, CRUD/soft-delete, request-limit, API-contract, rendering, and form exercises | P1 paired practice; P1 standalone practice | Phase 01 |
| [03 — PostgreSQL, JPA, and SQL migration](phase-03-data-sql.md) | One PostgreSQL schema, SQL progression, and data-access exercises; both T-SQL scripts deliberately adapted | P1 standalone/paired practice; P1 inventory coverage | Phase 01; Phase 02 API basics |
| [04 — Authentication and security](phase-04-security.md) | Safe auth/session and authorization modules with negative-path checks | P1 paired practice; P2 integrations/security boundary | Phases 02–03 |
| [05 — Business workflows](phase-05-workflows.md) | Small account/catalog/order/inventory, safe upload, CSV, and reporting modules; complex workflows clearly optional | P1 paired practice; P1 standalone practice; P3 no capstone | Phases 02–04 |
| [06 — Testing, Docker, and CI](phase-06-quality-devops.md) | Verification exercises and repaired/expanded local operations learning path | P1 independent verification; P1 local DevOps; P2 optional tooling | Phases 02–05 (tests may be authored alongside earlier phases) |
| [07 — Service and realtime integrations](phase-07-integrations.md) | Redis, RabbitMQ, Cloudinary, WebSocket, email, OAuth, scheduled jobs, and external API adapters as independent modules | P2 | Phases 03–06 |
| [08 — Payment lifecycle and providers](phase-08-payments.md) | Local fake lifecycle first, then optional PayOS/VNPay adapters, verification, and reconciliation | P2 | Phases 02 and 05; Phase 06 testing conventions are recommended. Phase 07 is optional. |

## Completion and acceptance gates

### Core checkpoint

The curriculum is useful before Extensions are attempted when the learner can complete and locally verify: Spring REST/layers/DTOs/errors/validation; a paired Next.js typed client, route, form and async states; PostgreSQL CRUD/schema/query basics and adapted SQL challenges; foundational auth/authorization; safe bounded file handling; a basic aggregate report; representative unit/API/component tests; and local Compose/CI basics. CSV bulk processing, account lock controls, refresh rotation, scheduled jobs, and external integrations remain Extensions. The Core boundary is an exit criterion, not a capstone application.

### Plan-to-implementation acceptance

- Use [inventory-map.md](inventory-map.md) as the source of truth: every spec inventory item A–H is assigned an exercise ID or a documented deferral; IDs have a phase, tier, difficulty, and paired/standalone shape.
- For each paired ID, publish one shared brief/API contract and independently verifiable Java and Next.js work areas. No task depends on a prior exercise's completed product code.
- SQL scripts and all new SQL challenges use PostgreSQL and the shared schema. A reviewer can trace each retained T-SQL challenge to its adaptation or an explicit omission reason.
- For every exercise, acceptance checks state observable behavior and a runnable command/check. Docker/vendor-account-dependent checks have a local alternative and prerequisite note.
- No task leaks secrets, stores credentials/tokens in localStorage, trusts a payment browser redirect as settlement proof, or requires live payment/provider credentials.
- Upload modules validate bounded size/content, generate storage names, persist only suitable metadata, and define replacement/cleanup behavior; file payloads are not stored as ordinary database blobs.
- CSV import/export defines row-level errors and explicitly chooses partial success or all-or-nothing transaction behavior. Scheduled work and notifications are safe to retry and expose observable state.
- Report queries apply filters in PostgreSQL and return a narrow DTO/projection; audit records identify actor/time/target and exclude secrets.
- Docs index links each track, concepts, shared contracts, and every exercise inventory section.
- Exercise artifacts are scaffolds/TODOs and checks, not complete answer implementations.

## Risks and mitigations

| Risk | Mitigation |
|---|---|
| Curriculum scope expands into a large commerce application | Keep exercises isolated, define a Core checkpoint, and tag integrations/business complexity as Extensions. |
| Paired work blocks on the other language or local service | Freeze a brief/API contract first; each side supports mock/stub verification. |
| SQL translation changes challenge intent or retains misleading SQL Server performance claims | Inventory original tasks, port schema/data and queries separately, validate against PostgreSQL, and replace absolute timings with controlled plan comparisons. |
| Auth/payment exercises teach unsafe shortcuts | State explicit security invariants and negative tests in the exercise brief; use maintained libraries and local fakes. |
| External services/accounts or Docker are unavailable | Core paths run against local fakes; vendor sandbox, Testcontainers, and E2E are optional and prerequisites are documented. |
| File import/upload, scheduled tasks, or reports expand into broad product features | Keep each as one bounded module with synthetic fixtures, explicit size/row limits, and no real provider or AI dependency. |
| Framework behavior changes over time | Pin versions in each runnable module and cite official docs in concept guides; review version-specific caching and deployment assumptions. |

## Official reference links for implementation briefs

- [Spring Boot web errors and Problem Details](https://docs.spring.io/spring-boot/reference/web/servlet.html), [Spring Security JWT Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Spring Security PasswordEncoder](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/password-encoder.html), [Spring Framework task scheduling](https://docs.spring.io/spring-framework/reference/integration/scheduling.html)
- [Next.js data fetching](https://nextjs.org/docs/app/getting-started/fetching-data), [Next.js backend-for-frontend deployment caveats](https://nextjs.org/docs/app/guides/backend-for-frontend)
- [PostgreSQL documentation](https://www.postgresql.org/docs/), [Flyway PostgreSQL driver](https://documentation.red-gate.com/flyway/reference/database-driver-reference/postgresql-database)
- [Spring Data Redis cache](https://docs.spring.io/spring-data/redis/reference/redis/redis-cache.html), [RabbitMQ acknowledgements/confirms](https://www.rabbitmq.com/docs/confirms), [Cloudinary client-side uploads](https://cloudinary.com/documentation/client_side_uploading), [Spring WebSocket/STOMP](https://docs.spring.io/spring-framework/reference/web/websocket/stomp.html)
- [PayOS documentation](https://payos.vn/docs/), [VNPay payment specification](https://sandbox.vnpayment.vn/apis/files/VNPAY%20Payment%20Gateway_Techspec%20Post%20method%202.1.0-VN.pdf)
- [Testcontainers Spring Boot/PostgreSQL example](https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/), [Playwright](https://playwright.dev/docs/next/intro), [GitHub Actions Java/Node guide](https://docs.github.com/en/actions/tutorials/build-and-test-code)
- [OWASP session management](https://cheatsheetseries.owasp.org/cheatsheets/Session_Management_Cheat_Sheet.html), [OWASP CSRF](https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html)
- [OWASP file uploads](https://cheatsheetseries.owasp.org/cheatsheets/File_Upload_Cheat_Sheet.html), [OWASP SQL injection prevention](https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html)
