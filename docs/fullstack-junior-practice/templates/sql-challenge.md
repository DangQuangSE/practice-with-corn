# {{ID}} — {{Title}}

| Field | Value |
|---|---|
| Tier / difficulty | `Core` or `Extension` / `Easy`, `Medium`, or `Challenge` |
| Shape | `Standalone` |
| Competency | {{Inventory section and competency}} |
| Dialect/schema | PostgreSQL / {{shared schema version}} |

## Objective

{{One SQL learning outcome.}}

## Fixture

{{Tables/data provided by the shared seed or a small local fixture.}}

## Task

{{Expected result columns, ordering, and edge cases; never rely on implicit row ordering.}}

## TODO

Write the query in `solution.sql`; do not include the completed query in this prompt.

## Acceptance criteria

- [ ] {{Observable rows/constraints}}
- [ ] {{Boundary or consistency condition}}

## Verify

```bash
psql "$DATABASE_URL" -v ON_ERROR_STOP=1 -f solution.sql
```

Expected: {{Observable result or check query.}}

## Hint

{{Progressive hint.}}
