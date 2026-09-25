# SEC-01–SEC-06 — Browser session and API security contract

**Java:** `java-business-rules/exercises/phase-04-browser-session/`
**Next.js:** `nextjs-business-rules/exercises/phase-04-access-states/`
**Trust boundary:** Java owns identity, session, authorization, CSRF, and resource ownership. Next.js only renders access states.

## Browser session flow

1. `GET http://localhost:8081/api/csrf` with `credentials: include` returns `{ "headerName": "X-CSRF-TOKEN", "parameterName": "_csrf", "token": "..." }` and establishes the CSRF/session context.
2. Submit a browser form to `POST /login` with `username`, `password`, and the current CSRF parameter. Java authenticates and sets an HttpOnly session cookie.
3. `GET /api/me` with `credentials: include` returns 200 and a minimal current-user DTO, 401 when anonymous/expired, or 403 when authenticated but forbidden.
4. `POST /api/session/logout` includes the current CSRF parameter; Java invalidates the session and clears the cookie. Fetch a fresh CSRF token after authentication/logout before the next unsafe request.

Cookie attributes: `HttpOnly; Secure; SameSite=Lax`. For local HTTP-only development, the Secure setting may be explicitly relaxed only in a local profile; production-like verification must use HTTPS and Secure=true.

## CORS and CSRF

- Allow exactly the configured frontend origin; credentialed CORS must not use `*`.
- Session cookie is automatically sent by the browser and is never readable by JavaScript.
- Keep Spring's CSRF protection enabled for cookie-authenticated mutations. Do not disable it to make a frontend request work.
- Store the CSRF token only in page memory/form state; it is not a login token. Do not persist credentials or tokens in local/session storage.
- Treat profile/user-controlled fields as untrusted text. Render them as text and do not bypass React escaping with `dangerouslySetInnerHTML` for this exercise.

## Access decisions

| Case | Expected result |
|---|---|
| No session / expired session | 401 from `/api/**` |
| Valid session, missing role/permission | 403 |
| Valid session but wrong resource owner | 403 (or non-disclosing 404 if the API contract chooses that policy) |
| Valid session and authorized owner | 2xx DTO |
| Missing/invalid CSRF on unsafe cookie-authenticated request | 403 |

## Independent verification

- Java: run the session module, sign in with a local synthetic user, inspect `Set-Cookie`, and try an unsafe request without/with CSRF.
- Next.js: stop Java to view the error state; use mock 401/403/200 responses to verify the UI. A hidden route is not proof of backend authorization.
