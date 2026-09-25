# FLOW-01 — Profile and duplicate conflict

**Pair:** Java owns validation, normalization, uniqueness, and safe error mapping; Next.js owns the form and feedback. Both sides can run independently with synthetic data.

## API brief

- `PUT /api/profiles/me`, JSON `{ "displayName": "...", "email": "..." }`.
- Success: `200` and the shared success envelope with `{ id, displayName, email }`; never return password/credential fields.
- Validation: `400` with field errors for malformed input; `409` with stable code `PROFILE_EMAIL_ALREADY_USED` for a duplicate.
- Decide and document email normalization before uniqueness lookup. Do not reveal the conflicting account's identity.
- Frontend: inline field errors, pending state, and safe duplicate message; no optimistic success before the server confirms.
- Mock scenario: first update succeeds, a normalized duplicate conflicts, a malformed email fails validation.
