# {{ID}} — {{Scenario}} shared brief and API contract

| Field | Value |
|---|---|
| Tier / difficulty | `Core` or `Extension` / `Easy`, `Medium`, or `Challenge` |
| Backend owner | `java-business-rules/exercises/{{ID}}/` |
| Frontend owner | `nextjs-business-rules/exercises/{{ID}}/` |
| API base URL | `http://localhost:8080` (unless this exercise says otherwise) |

## Shared scenario and objective

{{One short context and the learning objective for each side.}}

## Endpoint contract

| Method | Path | Request | Success | Errors |
|---|---|---|---|---|
| {{HTTP method}} | {{path}} | {{DTO or none}} | {{status and DTO}} | {{status and Problem Details shape}} |

## DTOs

```json
{{Example JSON only; do not include secrets or persistence internals.}}
```

## Ownership and independent work

- Backend TODO: {{Spring API behavior and backend verification command}}.
- Frontend TODO: {{Next.js behavior and frontend verification command}}.
- Mock contract: {{How either side can be checked without the other side running}}.

## Shared acceptance criteria

- [ ] {{Contract-level behavior}}
- [ ] {{Validation/auth/error behavior}}
- [ ] Each side can be run and checked independently.
