# Phase 04 — Authentication and security boundaries

**Stories:** P1 secure paired learning; P2 optional identity integrations; P1 safe local verification.
**Dependencies:** Phases 02–03.

## Deliverables

- SEC-01–SEC-06, SEC-08, SEC-10: registration/password hashing/login/logout/password change; JWT lifecycle using maintained libraries; role checks; resource ownership; safe browser session behavior; CSRF/CORS/XSS trust boundaries; sensitive-config review (mapped to BE-09/OPS-01); and account lock/unlock behavior.
- SEC-07 refresh rotation/revocation/logout as optional Challenge.
- FE-08 session-aware route/access states. Security tests in TEST-04 cover unauthenticated, authenticated, wrong-role, expired/invalid token, and cross-user resource denial.
- Publish an explicit local browser/API auth contract. The paired browser flow uses a Java-owned HttpOnly/Secure/SameSite session cookie with CSRF protection; the separate API exercise demonstrates Bearer JWT validation/expiry. Never persist tokens in localStorage/sessionStorage. A separate BFF comparison may be optional, not the default API path.
- Use Spring Security Resource Server and a maintained encoder/local test issuer for JWT issuance/validation; do not implement a custom JWT filter or signature cryptography manually. Use Spring Security `PasswordEncoder` (BCrypt for the baseline hashing exercise).

## Acceptance criteria

- Learner can distinguish 401 from 403 and prove resource ownership is enforced by Java, not just hidden by the UI.
- Browser session, CORS and CSRF behavior is specified and locally testable for the chosen origin/proxy setup.
- Security checks cover allow and deny cases, including expiry and tampering, without storing secrets in source control.
- Password change/logout and account lock/unlock have explicit session/token invalidation and denied-login checks; reset/email verification may use Phase 07's local mail sink.
- Auth remains independently runnable using local users/keys/fixtures; no OAuth provider account is required.

## Design Constraints

- No plaintext/reversible passwords, homemade crypto, production identity, real customer data, or browser localStorage token persistence.
- Do not treat route guards as authorization; every protected API operation enforces access server-side.
- Cookie-authenticated state changes must include a CSRF defense; credentialed CORS must have explicit origins.

## Quality and Testing State

- Quality: not evaluated
- Testing: not started
