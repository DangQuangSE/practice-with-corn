# PostgreSQL query and schema basics

Relational constraints are executable domain rules: primary/foreign keys preserve identity and relationships; `NOT NULL`, `UNIQUE`, and `CHECK` constrain values. PostgreSQL uses `GENERATED ... AS IDENTITY` for generated keys and `timestamptz` for instants that need an unambiguous time-zone-aware representation.

Filter and order before paginating; always define an explicit deterministic order. Use bound parameters for values and allowlists for dynamic identifiers such as sort columns. SQL aggregations operate on rows after joins, so know whether a one-to-many join multiplies values before summing.

`EXPLAIN (ANALYZE, BUFFERS)` executes the statement and reports observed work. A plan is data- and environment-dependent; a sequential scan can be correct for a small table. Use synthetic local fixtures, and wrap mutations in transactions while learning.

## Practice

| Exercise | Apply |
|---|---|
| [SQL-01–SQL-03](../../../sql-practice/README.md) | Constraints, filters, and safe DML |
| [SQL-04–SQL-08](../../../sql-practice/README.md) | Joins, aggregates, windows, and transactions |
| [SQL-09–SQL-12](../../../sql-practice/README.md) | Plans, migrations, pagination, and legacy challenge translation |
| [BE-11–BE-13](../../../java-business-rules/exercises/phase-03-data-access/README.md) | JPA relationships, transactions, and fetch behavior |

## Further reading

- [PostgreSQL 18 documentation](https://www.postgresql.org/docs/18/)
- [PostgreSQL version support policy](https://www.postgresql.org/support/versioning/)
