# Phase 02 — Spring REST foundations

**IDs:** BE-01–BE-10, BE-17, BE-18  
**Tier:** Core, except BE-07 (Extension)  
**Prerequisites:** Java 21+, Maven 3.6.3+; no external database or credential is required.

This is a fresh, isolated Spring Boot module. It is not shared application code for later phases. The controller intentionally returns `501 Not Implemented` until the relevant TODOs are completed.

## Run and build

```bash
mvn spring-boot:run
```

The server listens on `http://localhost:8080`. Build/syntax check:

```bash
mvn -DskipTests package
```

The `-DskipTests` flag is used for a compile/package check only; it does not verify exercise behavior. No unit tests were run during curriculum authoring.

## Exercises

| ID | Exercise and TODO | Acceptance / local verification |
|---|---|---|
| [BE-01](#be-01--http-resource-api) | Add resource routes, HTTP methods, status codes, and request/response DTOs. | `curl.exe -i http://localhost:8080/api/items` shows a documented status and JSON contract; no entity is returned. |
| [BE-02](#be-02--layers-and-dependency-injection) | Separate controller, use-case/service, and repository responsibilities; inject dependencies through constructors. | Application context starts; controller contains no storage/business rules. |
| [BE-03](#be-03--crud-not-found-and-conflict) | Complete create/read/update behavior with explicit not-found and duplicate/conflict handling. | Try a missing UUID and duplicate item name; compare status and response to contract. |
| [BE-04](#be-04--success-envelope-and-problem-details) | Apply the shared success envelope and RFC 9457 Problem Details without exception or stack disclosure. | Trigger validation and an unexpected failure; inspect `Content-Type` and fields. |
| [BE-05](#be-05--request-validation) | Complete request constraints and stable field-level error mapping. | Send blank/overlong name and negative price; each invalid field is identified. |
| [BE-06](#be-06--bounded-search-pagination-and-sort) | Add search, status/date filters, bounded paging, allowlisted sorting; compare `Page` and `Slice`. | Requests reject invalid sort/filter values; page metadata and results match the shared contract. Phase 03 replaces this starter's in-memory work area with a database query. |
| [BE-07](#be-07--request-id-and-safe-correlation-logs) | Register the filter, choose caller-ID validation/reuse rules, and correlate logs. | Response contains a request ID; tests/log review show no password, token, or credential is emitted. |
| [BE-08](#be-08--cors-and-security-headers) | Configure a narrow origin allowlist and basic security headers. | Allowed local origin succeeds; an unlisted origin receives no permissive CORS headers; no wildcard with credentials. |
| [BE-09](#be-09--profiles-and-server-only-configuration) | Separate local/default configuration from environment-specific settings; use environment variables for secrets. | Start with the `local` profile; search the repository/browser bundle for secrets (there should be none). |
| [BE-10](#be-10--openapi-contract) | Complete [`openapi.yaml`](openapi.yaml) and keep it aligned with endpoint DTOs/statuses. | Validate the document with Swagger Editor or another OpenAPI 3.1 validator; compare one request with the running API. |
| [BE-17](#be-17--soft-delete-and-restore) | Define deleted/restore behavior, repeated calls, and access/error semantics. | Delete then read/list, restore, and repeat each operation; observed state matches the policy you document. |
| [BE-18](#be-18--request-body-size-limit) | Enforce the configured 64 KiB request cap, including requests without a trustworthy `Content-Length`. | A small body reaches validation; a larger body returns 413 or documented container-equivalent Problem Details, never a stack trace. |

## Contract and detail

Paired contracts: [BE-01](../../../docs/fullstack-junior-practice/contracts/BE-01.md), [BE-03](../../../docs/fullstack-junior-practice/contracts/BE-03.md), [BE-04](../../../docs/fullstack-junior-practice/contracts/BE-04.md), [BE-05](../../../docs/fullstack-junior-practice/contracts/BE-05.md), [BE-06](../../../docs/fullstack-junior-practice/contracts/BE-06.md), [BE-10](../../../docs/fullstack-junior-practice/contracts/BE-10.md).

### BE-01 — HTTP resource API

Implement collection/detail/create/update endpoints under `/api/items`. Use plural nouns, the appropriate HTTP method, correct status (`200`, `201`, `204`, `400`, `404`, `409`), and DTOs. For creation, return a `Location` header. Keep an endpoint's request and response stable if the internal model changes.

### BE-02 — Layers and dependency injection

Move use-case decisions out of the controller. Add a small service interface/implementation and constructor-injected repository boundary. Avoid an interface for every class unless there is a real substitution/test seam.

### BE-03 — CRUD, not-found, and conflict

Choose a deterministic uniqueness rule for item names. Return 404 when an ID does not exist and 409 when a unique value is already taken. Do not return `null` as a successful API response.

### BE-04 — Success envelope and Problem Details

Use the documented success shape consistently; omit pagination metadata for non-page responses. Map expected errors to meaningful 4xx responses and unexpected errors to a generic 500. Never send stack traces, SQL, or class names to callers.

### BE-05 — Request validation

Validate the HTTP request at the boundary with Jakarta Validation. Define which fields are required, their length/range constraints, and a deterministic field-error format. Validation errors are client errors, not 500s.

### BE-06 — Bounded search, pagination, and sort

Define `page` as zero-based, default `size=20`, maximum `size=100`. Add `q`, `status`, `from`, and `to`. Allow only documented sort fields; never concatenate user-provided SQL/property names. Explain when exact total counts make `Page` useful and when `Slice` avoids count-query cost. Move filtering/order/page operations into the database in Phase 03.

### BE-07 — Request ID and safe correlation logs

Register `RequestIdFilter`. Define how to reject or regenerate malformed caller IDs. Propagate one identifier through response and structured logs. Do not log authorization headers, cookies, passwords, or raw request bodies.

### BE-08 — CORS and security headers

Use `FRONTEND_ORIGIN` to allow the local frontend origin only. Add headers such as `X-Content-Type-Options: nosniff` and an appropriate `Content-Security-Policy` for the API. Do not combine wildcard origins with credentials.

### BE-09 — Profiles and server-only configuration

Use Spring profiles and environment placeholders; `.properties` files contain safe defaults only. Secrets must never use a `NEXT_PUBLIC_` prefix or enter a frontend bundle.

### BE-10 — OpenAPI contract

Describe parameters, schemas, required fields, success/error statuses, and examples in `openapi.yaml`. Keep the contract human-reviewable; no live Postman workspace or account is required.

### BE-17 — Soft delete and restore

Choose whether deleted rows are hidden from ordinary reads and how repeat-delete/restore behaves. Preserve the record for restoration. Document authorization and 404/409 cases even while authentication is added in Phase 04.

### BE-18 — Request body size limit

Configure an explicit byte limit. Reject oversized JSON before unbounded buffering; handle chunked/unknown-length requests rather than trusting only the `Content-Length` header. Map rejection to 413 and the common error shape.

## TODO work area

- `api/ItemController.java` — routes and response mapping.
- `api/ApiExceptionHandler.java` — stable Problem Details and field errors.
- `web/RequestIdFilter.java` — registration, validation, and correlation.
- `web/RequestBodyLimitFilter.java` — bounded request handling and 413.
- `openapi.yaml` — complete the API contract.

## Hints

1. Define the HTTP contract before choosing the Java class shape.
2. Keep DTO validation and domain conflict rules distinct.
3. Treat caller-provided headers and query fields as untrusted input.
4. For pagination, validate the page-size and sort allowlist before repository execution.

## References

- [Spring Boot 4.1.1 system requirements](https://docs.spring.io/spring-boot/system-requirements.html)
- [Spring MVC and Problem Details](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [OpenAPI Specification](https://spec.openapis.org/oas/v3.1.0.html)
