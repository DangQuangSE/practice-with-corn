# FILE-01 — Safe bounded upload

**Owner:** Java backend; standalone exercise. Phase 07 may later replace local storage with a provider adapter.

- `POST /api/files` uses multipart field `file`; limit 5 MiB and accept only the documented image/PDF allowlist.
- Success `201` returns metadata `{ id, displayName, contentType, size, createdAt }`, never a local filesystem path.
- Reject too-large input with `413`, unsupported/signature-mismatched content with `415`, malformed input with `400`.
- MIME declaration and filename are untrusted. Generate storage key server-side, inspect bounded content signatures, store outside the web root, and persist metadata only.
- Replacement and cleanup must have explicit recoverable behavior. Use only harmless synthetic fixtures.
