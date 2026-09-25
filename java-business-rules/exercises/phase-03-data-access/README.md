# Phase 03 — PostgreSQL, JPA, and transactions

**IDs:** BE-11–BE-13, TEST-05, SQL-01–SQL-12  
**Runtime:** Java 21, Spring Boot 4.1.1, Maven 3.6.3+, PostgreSQL 18.6.  
**Prerequisite:** Complete the local PostgreSQL setup in [`sql-practice/README.md`](../../../sql-practice/README.md).

The JPA module uses Flyway with `ddl-auto=validate` and maps the shared schema; it does not create schema from entities. Build without a running database:

```bash
mvn -DskipTests package
```

To start it, set `SPRING_DATASOURCE_PASSWORD` to the local-only password from `sql-practice/.env`, then run `mvn spring-boot:run` from this module. Do not copy that local password into committed files.

## Exercises

| ID | Focus | TODO and acceptance |
|---|---|---|
| BE-11 | Relationship cardinality, association entity, DTO/entity boundary | Inspect `AccountEntity`, `PurchaseOrderEntity`, `ProductEntity`, and `OrderLineEntity`. Explain 1:N/N:1 cardinalities, why `OrderLineEntity` owns quantity/unit price, and why cascade is limited. Add a mapper/projection; never serialize the entity graph. |
| BE-12 | Transaction boundary, rollback, isolation | Complete one local multi-step state update. Demonstrate all-or-nothing rollback; compare READ COMMITTED with REPEATABLE READ in a local DB. Keep remote calls outside the transaction. |
| BE-13 | N+1 and fetch strategy | Observe SQL while mapping summaries; compare lazy traversal with EntityGraph, fetch join, or a narrow projection. Do not globally mark every relationship eager. |
| TEST-05 | PostgreSQL persistence integration | Add a Testcontainers-backed persistence/rollback check when Docker is available; document a local PostgreSQL/manual fallback. This test exercise is authored later with reusable test tooling in Phase 06. |
| SQL-01–SQL-12 | SQL track | See [`sql-practice/README.md`](../../../sql-practice/README.md), the shared schema/migration, and `sql-practice/exercises/`. |

## TODO work area

- Improve the DTO projection in `service/OrderReadService.java` and verify SQL query count.
- Complete the atomic use case in `service/OrderTransactionService.java` and identify rollback conditions.
- Add one migration `V2__...sql` through SQL-10; never use entity auto-DDL as the migration mechanism.
- Explain how a remote payment/email call would be coordinated without holding a DB transaction open.

## Hints

1. A relationship that is convenient to navigate is not automatically a good JSON response.
2. An association with its own columns should be modeled as an entity, not a bare `@ManyToMany`.
3. Fetch only what the use case needs; inspect generated SQL before choosing a fix.

## TEST-05 local alternatives

- Preferred: run the test module against a disposable PostgreSQL Testcontainer (Docker required).
- Alternative: use the local PostgreSQL 18.6 Compose service and explicitly reset only its named practice volume when clean state is needed.
- Expected checks: Flyway applies V1; repository insert/read works; failed multi-step update rolls back; no test uses production data.
