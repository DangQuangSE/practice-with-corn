# Phase 05 — Workflow UI practice

**IDs:** FE-09, FE-10, FE-12, FE-14, FE-15. **Runtime:** Node.js 24.21.0, Next.js 16.3.6, React 19.2, TypeScript 5.9.

This isolated App Router workspace uses local mock data and runs without Java. The source has clear TODOs for URL-driven search, optimistic rollback, the report API, and CSV import. It is not a BFF; paired screens call the Java API directly when you implement them.

## Run and verify

```bash
npm ci
npm run dev
npm run typecheck
npm run build
```

## Exercise goals

- **FE-09:** query/filter state survives reload and browser navigation; invalid URL values are normalized.
- **FE-10:** render a provisional change, handle pending/error states, and roll back on server rejection.
- **FE-12:** retain visible focus, semantic table headers/caption, live status, labels, and usable narrow-screen layout.
- **FE-14:** connect [`REPORT-01`](../../../docs/fullstack-junior-practice/contracts/REPORT-01.md) and implement loading/empty/error/populated states.
- **FE-15:** show the selected partial/atomic import mode, bounded upload result, sanitized row errors, and explicit retry action.

The interaction is intentionally a mock; no file is sent to a server and no business state is persisted.
