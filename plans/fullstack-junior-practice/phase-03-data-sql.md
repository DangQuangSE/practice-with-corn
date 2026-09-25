# Phase 03 — PostgreSQL, JPA, and SQL migration

**Stories:** P1 independent SQL practice; P1 paired data/API practice; P1 all-new SQL uses one dialect.
**Dependencies:** Phase 01; Phase 02 API basics.

## Deliverables

- PostgreSQL setup notes, one shared learning schema/seed, and SQL-01–SQL-12 covering modeling/constraints, selection, DML, joins, aggregation, subqueries/CTEs, window functions, transactions, indexes/plans, migrations/seeds/corrections, pagination, and adapted challenges.
- Inventory `day1.sql` and `sql_demo.sql` statement-by-statement before porting. Preserve useful problem intent/data relationships; translate SQL Server-specific types, identity/defaults, date functions, random/unique seed generation, batch/print syntax, pagination, and execution-plan language to PostgreSQL equivalents.
- Remove platform-specific fixed latency targets and SQL Server named-plan expectations. Teach comparison with `EXPLAIN (ANALYZE, BUFFERS)` on stated data and environment; million-row seed/performance run is Challenge/optional.
- Java exercises BE-11–BE-13: common JPA cardinalities, an association entity with its own fields, fetch/cascade choices, and DTO mapping; transaction/rollback/isolation boundaries; spot and address N+1 with an appropriate fetch strategy. Pair the mapping exercise with SQL schema tasks where helpful.
- Search and report query exercises use bound parameters; any dynamic column/sort choice is allowlisted rather than concatenated into SQL/HQL.
- TEST-05: PostgreSQL persistence/transaction integration exercise using Testcontainers when Docker is available, plus a documented local alternative for learners who cannot run containers.
- Use Flyway for migration practice and explain schema lifecycle; no schema auto-generation as the sole migration mechanism.

## Acceptance criteria

- Every runnable/new SQL task executes against PostgreSQL and the documented schema.
- Both legacy files are either adapted with a source-to-new-ID map or specific useful content is deferred with a reason.
- Java mapping exercises demonstrate the selected relationship/fetch behavior, an association entity with its own fields, and the chosen cascade behavior without serializing entities as API contracts. Transaction exercises test rollback/isolation and keep remote service calls outside long-running database transactions.
- Integration-test setup states Docker and runtime prerequisites and has an alternative verification route.

## Design Constraints

- PostgreSQL only; do not mix T-SQL syntax in learner-facing runnable SQL.
- Performance claims must be reproducible in relative/contextual terms, not an absolute millisecond gate.
- Use bounded seed sizes by default. Any large generator must be opt-in and safe to reset locally.

Preflight: The current SQL scripts are SQL Server-flavored and the repository has no PostgreSQL exercise harness yet. Existing DevOps Compose is a hand-written learning draft with local credentials; do not reuse those credentials/configuration. Existing Java exercises use small TODO scaffolds with a learner-readable README. Canonical schema/migrations will live under `sql-practice`, use PostgreSQL 18.6, deterministic bounded seed data, and no required database test run; optional large data remains opt-in.

## Quality and Testing State

- Quality: skipped by user; no `ck:quality` gate was run.
- Testing: not started; unit tests were skipped by user. Maven package and SQL script execution were used as build/syntax checks only.
