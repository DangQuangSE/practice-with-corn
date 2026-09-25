# DATA-01 — Bounded CSV import

**Pair:** Java validates/imports; Next.js selects a file and summarizes row-level results. This is an optional Extension.

- `POST /api/imports` multipart CSV plus explicit mode `PARTIAL_SUCCESS` or `ALL_OR_NOTHING`; maximum 2 MiB and 10,000 rows.
- Response includes `{ accepted, rejected, mode, errors: [{ rowNumber, field, safeMessage }] }`.
- Parse quoted values correctly, validate headers/rows, define duplicate behavior, and never return raw stack traces or row secrets.
- Partial mode commits only accepted rows; atomic mode commits nothing if any row fails. State this in UI before upload.
- FE states include invalid file, pending, completed with errors, and downloadable sanitized error report. No automatic re-upload on retry.
