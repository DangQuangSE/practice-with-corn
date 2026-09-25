# Phase 04 — Browser session, CORS, and CSRF

**IDs:** SEC-05–SEC-06. **Runtime:** Java 21, Spring Boot 4.1.1.  
This is a stateful cookie-session module and must stay separate from the stateless Bearer API exercise.

## Local configuration

Copy `.env.example` to `.env`, set a unique synthetic password locally, then start this module. The example leaves the password blank so it cannot be used as a known credential:

```bash
mvn -DskipTests package
mvn spring-boot:run
```

The API uses port 8081; the paired Next.js client defaults to port 3000. `FRONTEND_ORIGIN` defaults to exactly `http://localhost:3000`. Do not use a wildcard origin with credentials. `SESSION_COOKIE_SECURE` defaults to true; if a browser cannot send Secure cookies over local HTTP, use a local HTTPS setup or explicitly relax Secure only for localhost development.

## What is already wired

- Spring Security form login with a BCrypt-encoded synthetic in-memory user sourced from environment configuration.
- Session cookie marked HttpOnly, Secure by default, and SameSite=Lax.
- CSRF protection remains enabled; `/api/csrf` returns the current CSRF header/parameter names and token for the paired practice client.
- Credentialed CORS allows only the configured frontend origin and a small method/header list.
- `/api/me` requires a valid session and returns only the current synthetic username; API unauthenticated requests return 401.
- `/api/session/logout` requires CSRF and invalidates the session through Spring Security logout.

## Exercises

Follow [SEC-01–SEC-06 browser contract](../../../docs/fullstack-junior-practice/contracts/SEC-01-to-06-browser-session.md). Verify 401/403/200, missing CSRF, wrong origin, cookie attributes, login, logout, and expired-session behavior. Add a role-protected route and ownership check on the backend; hiding a Next.js link does not authorize an API request.

Spring Security uses an in-memory synthetic account only to make local practice possible. Do not copy that storage choice into a real application. OAuth and email verification remain separate optional exercises in Phase 07.

## Reference

- [Spring Security CSRF](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html)
- [OWASP CSRF Prevention](https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html)
