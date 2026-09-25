# T-SQL source adaptation map

The current `day1.sql` and `sql_demo.sql` are now PostgreSQL curriculum files. This table records how their useful source intent was carried forward.

## `day1.sql`

| Source challenge | Preserved intent | Exercise mapping | PostgreSQL change |
|---|---|---|---|
| 1 — completed orders in recent period | Date filter, status filter, sort, compare an index/query plan | SQL-02, SQL-09, SQL-12 | `now() - interval '30 days'`; use `EXPLAIN (ANALYZE, BUFFERS)`; no 15 ms target or Index Seek requirement. |
| 2 — order/transaction reconciliation | Join by order and detect amount mismatch | SQL-04, SQL-08, SQL-12 | Standard PostgreSQL joins and `numeric`; no prescribed Merge Join/Hash Match. |
| 3 — top three orders by account | Per-account ranking | SQL-07, SQL-12 | `row_number`/`rank` window function; deterministic tie-breaker is an exercise choice. |
| 4 — rapid consecutive transactions | Per-account time ordering and velocity detection | SQL-07, SQL-12 | `lag` plus interval comparison; define whether the span is between first/third event and document ties. |
| 5 — product revenue share within category | Aggregate product and category revenue | SQL-05, SQL-06, SQL-07, SQL-12 | Join through `order_lines`; use `numeric` and window/CTE approaches; remove unsupported tempdb/RAM promises. |

## `sql_demo.sql`

| Source content | Preserved intent | Exercise mapping | PostgreSQL change |
|---|---|---|---|
| accounts/products/orders/transactions tables and relationships | Account, catalog, order, payment data model | SQL-01, SQL-04, SQL-10 | Portable types, identity columns, explicit PK/FK/check constraints; products in an order are represented by `order_lines` with quantity and unit price. |
| 10,000 accounts and 500 products | Synthetic lookup fixtures | SQL-10 | Small deterministic seed is default; generator variability and identity are not needed for correctness. |
| 1,000,000 orders and linked transactions | Scale for optional plan comparison | SQL-09, optional large seed | Bounded fixture is default; a separate opt-in generator is provided. |
| `IDENTITY`, `DATETIME`, `GETDATE`, `DATEADD`, `NEWID`, `CHECKSUM`, `TOP`, `PRINT`, `GO` | Identity, timestamps, test data, batches, and feedback | SQL-02, SQL-03, SQL-10, SQL-12 | `GENERATED ... AS IDENTITY`, `timestamptz`, `now()`/intervals, `generate_series`, `random`, `LIMIT`, psql `\echo`/`\ir`. |

No useful source challenge was dropped. Large data and query-plan comparisons are optional because runtime depends on hardware, data distribution, statistics, and PostgreSQL release.
