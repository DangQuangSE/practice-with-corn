# Phase 05 — CSV import and reporting boundary

**IDs:** DATA-01 (Extension), REPORT-01 (Core paired query). **Difficulty:** Medium. **Runtime:** Java 21, Spring Boot 4.1.1.

`CsvImportService` is the DATA-01 work area. Choose partial-success or all-or-nothing semantics per request; return row numbers and sanitized messages, never stack traces or submitted secrets. Limits are 2 MiB and 10,000 data rows.

REPORT-01's PostgreSQL query scaffold is in [`sql-practice/exercises/REPORT-01.sql`](../../../sql-practice/exercises/REPORT-01.sql). It uses the shared synthetic `orders` table. Complete the SQL projection and expose only the narrow summary DTO defined by the shared contract.

## Acceptance

- CSV quoting, escaped quotes, blank lines, invalid headers, malformed numbers, duplicate keys, and row/file limits are explicit.
- Partial and atomic modes have observably different documented outcomes; error output does not reveal server details.
- Report filters are applied in PostgreSQL, dates use a documented inclusive/exclusive convention, and no matching rows return a stable empty result.
- Run `mvn -DskipTests package` from this directory for the compile/package check.
