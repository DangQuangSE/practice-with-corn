# Phase 04 — Session-aware access states

**IDs:** FE-08, SEC-05/06 paired client. **Runtime:** Node.js 24.21.0, Next.js 16.3.6.  
Independent frontend workspace; it can build without Java running.

## Run/build

```bash
npm ci
npm run dev
npm run typecheck
npm run build
```

The Java browser-session module listens on `http://localhost:8081`. The browser form uses Spring's CSRF parameter; only the current CSRF value is kept in component memory. The HttpOnly session cookie is not accessible to JavaScript.

## TODO exercises

- [FE-08] Render anonymous, authenticated, forbidden, and expired-session states from Java API responses.
- Add an accessible re-auth/retry path and clear pending/error state.
- Use `credentials: "include"` only for the explicit Java API origin.
- After login/logout, fetch a fresh CSRF token; Spring clears the old token on those transitions.
- Demonstrate that a hidden route is UX only: a direct unauthorized API call must still fail at Java.
- Never store passwords, session IDs, JWTs, or refresh tokens in local/session storage.

The current client includes intentional TODO states. Replace them incrementally; do not turn the frontend into an authorization authority.
