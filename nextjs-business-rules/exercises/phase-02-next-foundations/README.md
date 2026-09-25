# Phase 02 — Next.js App Router foundations

**IDs:** FE-01–FE-07, FE-12, FE-13  
**Tier:** Core  
**Prerequisites:** Node.js 24.21.0 recommended (Next.js minimum: 20.9); Java API is optional because this starter includes a mockable contract.

This is an isolated App Router workspace. The root page runs without the Java API; implement and verify one competency at a time. The initial project is a scaffold, not a finished frontend.

## Run and build

```bash
npm ci
npm run dev
```

Open `http://localhost:3000`. Build/syntax checks:

```bash
npm run typecheck
npm run build
```

Set `NEXT_PUBLIC_API_BASE_URL=http://localhost:8080` only when using the public local API URL. Never put a secret under a `NEXT_PUBLIC_` variable.

## Exercises

| ID | Exercise and TODO | Acceptance / local verification |
|---|---|---|
| [FE-01](#fe-01--app-router-routes-and-layouts) | Extend App Router routes/layouts and dynamic params. | Directly open a nested route, refresh it, and verify not-found behavior. |
| [FE-02](#fe-02--server-and-client-component-boundary) | Choose Server vs Client Components; keep browser-only state in a small client leaf. | Inspect rendered behavior and ensure server-only values are not imported into a client component. |
| [FE-03](#fe-03--server-versus-browser-fetching) | Compare server-side and browser-side fetch for the same Java contract. | Stop the backend and verify the failure is visible and does not crash unrelated routes. |
| [FE-04](#fe-04--typed-api-client) | Complete typed success and Problem Details parsing. | A mocked response matches the shared DTO; non-2xx responses keep status/field errors available to the UI. |
| [FE-05](#fe-05--async-and-not-found-states) | Render loading, empty, error, success, and not-found states. | Exercise each state with mock responses or by stopping the Java API. |
| [FE-06](#fe-06--cache-and-revalidation) | Choose explicit cache/revalidation behavior and refresh stale data after a mutation. | Create/update then revisit the list; document which layer invalidates or refetches data. |
| [FE-07](#fe-07--form-validation-and-backend-errors) | Validate fields and render Java field-level errors accessibly. | Submit invalid and valid mock responses; focus remains usable and pending submissions cannot duplicate. |
| [FE-12](#fe-12--accessible-responsive-forms-and-lists) | Improve keyboard, label, status/error announcement, and narrow viewport behavior. | Complete all actions by keyboard and inspect at 320px width. |
| [FE-13](#fe-13--server-secret-boundary) | Identify public config versus server-only secret and prevent secret bundle exposure. | Search `.next/static` and source for a synthetic secret marker; the marker must not appear. |

## Contract

Use [BE-01](../../../docs/fullstack-junior-practice/contracts/BE-01.md), [BE-03](../../../docs/fullstack-junior-practice/contracts/BE-03.md), [BE-04](../../../docs/fullstack-junior-practice/contracts/BE-04.md), [BE-05](../../../docs/fullstack-junior-practice/contracts/BE-05.md), [BE-06](../../../docs/fullstack-junior-practice/contracts/BE-06.md), and [BE-10](../../../docs/fullstack-junior-practice/contracts/BE-10.md) as relevant. If Java is unavailable, implement a small local mock that returns the same shapes.

### FE-01 — App Router routes and layouts

Add a nested `/items/[id]` route with typed params and a not-found page. Keep navigation and route naming consistent; direct navigation and refresh must work.

### FE-02 — Server and Client Component boundary

Keep the page/list shell server-rendered. Use a small Client Component for form state/interactions only. Explain why each component needs or does not need `'use client'`.

### FE-03 — Server versus browser fetching

Choose one read path and one mutation path. Compare server fetch with browser fetch for cookie/CORS exposure, loading UX, and cache semantics; do not assume every fetch is cached.

### FE-04 — Typed API client

Complete `src/lib/api.ts` to parse the success envelope and Problem Details without converting every backend failure into an unhelpful generic message. Keep request/response types aligned with the shared contract.

### FE-05 — Async and not-found states

Replace the status placeholder on `/items` with loading, empty, error, success, and not-found states. Errors need a retry/recovery path where appropriate.

### FE-06 — Cache and revalidation

Declare cache behavior at the fetch boundary. After a mutation, make the chosen list/detail view fresh using the appropriate server revalidation or client refetch; document stale-data trade-offs.

### FE-07 — Form validation and backend errors

Add client-side schema/field constraints as UX, while treating backend validation as authoritative. Map `errors[].field` to fields; summarize errors for assistive technology.

### FE-12 — Accessible responsive forms and lists

Use visible labels, logical tab order, clear focus, semantic headings, announced async/error state, usable touch targets, and a narrow-screen layout.

### FE-13 — Server secret boundary

Keep server credentials in server-only code/environment variables. Only non-sensitive configuration such as API origin may be `NEXT_PUBLIC_`. Verify that a marker value cannot be found in browser assets.

## Hints

1. App Router components are Server Components unless a client boundary is requested.
2. Model the API response once; avoid duplicating ad-hoc `any` shapes in each component.
3. Treat a request's pending state as part of the UI contract.

## References

- [Next.js 16 installation and requirements](https://nextjs.org/docs/app/getting-started/installation)
- [Next.js data fetching](https://nextjs.org/docs/app/getting-started/fetching-data)
- [Next.js release/support policy](https://nextjs.org/support-policy)
- [Node.js release schedule](https://nodejs.org/en/about/previous-releases)
