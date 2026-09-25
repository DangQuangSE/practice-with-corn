# Phase 05 — Safe bounded file upload

**ID:** FILE-01. **Tier:** Core. **Difficulty:** Medium. **Runtime:** Java 21, Spring Boot 4.1.1.

Complete the validation/storage TODOs using harmless local files only. The module deliberately has no Cloudinary SDK, credentials, or persistent shared state; the adapter is a Phase 07 exercise.

## Acceptance

- Reject empty, oversized, disallowed, and signature-mismatched files. Browser MIME and filename are untrusted.
- Use a server-generated opaque storage key; never concatenate a user filename into a filesystem path.
- Store metadata (owner, generated key, safe display name, media type, byte size, timestamp), not file bytes in an ordinary database column.
- Define replacement as a recoverable sequence; cleanup failure must not orphan an inaccessible record silently.
- Keep storage outside the web root and cap multipart request size as well as individual file size.

## Verify

From this directory run `mvn -DskipTests package`. When implementing, add deterministic cases for boundary size, signature mismatch, replacement, and cleanup. Never upload executable or sensitive files.
