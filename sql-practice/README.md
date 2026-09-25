# SQL Practice — PostgreSQL 18.6

This is the only SQL learning track. It adapts the useful schema, data relationships, and five analytics prompts from the previous SQL Server scripts to PostgreSQL. No challenge depends on SQL Server syntax or execution-plan terminology.

## Start locally

Requires Docker Compose. Copy `.env.example` to `.env` and set a unique non-empty local password before starting the database. The port is bound to loopback (`127.0.0.1`) only. Then run from the repository root:

```powershell
Copy-Item sql-practice/.env.example sql-practice/.env
docker compose --env-file sql-practice/.env -f sql-practice/docker-compose.yml up -d postgres
docker compose --env-file sql-practice/.env -f sql-practice/docker-compose.yml exec -T postgres psql -U practice -d practice -v ON_ERROR_STOP=1 -f /workspace/sql_demo.sql
```

The container exposes PostgreSQL on host port `5433`. The `.env` password is for this local practice database only; do not reuse it elsewhere. The named volume persists between runs. To remove only this lab's data, from the repository root run `docker compose --env-file sql-practice/.env -f sql-practice/docker-compose.yml down -v`; this permanently removes the local practice database volume.

Without Docker, install PostgreSQL 18 and run `psql -d practice -v ON_ERROR_STOP=1 -f sql-practice/sql_demo.sql` after creating a local database.

## Files

- [`schema.sql`](schema.sql): canonical schema, also used by the Java Flyway exercise.
- [`seed.sql`](seed.sql): small deterministic synthetic fixture; idempotent and safe to rerun.
- [`sql_demo.sql`](sql_demo.sql): psql entry point that includes schema and seed.
- [`day1.sql`](day1.sql): five translated analytics challenge prompts from the former T-SQL exercise.
- [`source-map.md`](source-map.md): statement/challenge traceability for both original scripts.
- [`exercises/`](exercises/): SQL-01–SQL-12 TODO query files.
- [`optional/seed-large.sql`](optional/seed-large.sql): opt-in 100,000-row performance fixture; do not run by default.

Run an individual completed query from the repository root:

```powershell
docker compose --env-file sql-practice/.env -f sql-practice/docker-compose.yml exec -T postgres psql -U practice -d practice -v ON_ERROR_STOP=1 -f /workspace/exercises/SQL-04-joins.sql
```

## Exercise conventions

- PostgreSQL 18.6 only; all dates use `timestamptz` and interval arithmetic.
- Default fixture is intentionally small. Performance work uses relative plan comparisons with `EXPLAIN (ANALYZE, BUFFERS)`, not fixed latency thresholds.
- Index plans depend on data size, distribution, statistics, and PostgreSQL version; a sequential scan is not automatically a bug.
- Scripts do not drop tables or truncate data. Review every mutation before running it.
- Use the shared schema; don't introduce a duplicate SQL Server practice track.

Inventory: [SQL-01–SQL-12](../plans/fullstack-junior-practice/inventory-map.md#g-sql-practice-postgresql). Official references: [PostgreSQL 18 documentation](https://www.postgresql.org/docs/18/), [version support policy](https://www.postgresql.org/support/versioning/).
