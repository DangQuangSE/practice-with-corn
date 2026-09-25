# REPORT-01 — Filtered order report

**Pair:** Java query/projection owner; Next.js summary-table owner. Independent mock mode is required.

- `GET /api/reports/orders?from=&to=&status=` accepts an inclusive `from` and exclusive `to` timestamp and optional status.
- Return one summary per status: `{ status, orderCount, totalAmount, averageAmount }` in the shared success envelope.
- Apply filters and aggregation in PostgreSQL; use a narrow projection, not full entity serialization. Parameterize values.
- Empty ranges return an empty list, not an error. Reject invalid/reversed dates and unsupported status with `400`.
- FE states: loading, empty, error, populated; labels/table headers are accessible and amounts are formatted from decimal strings.
