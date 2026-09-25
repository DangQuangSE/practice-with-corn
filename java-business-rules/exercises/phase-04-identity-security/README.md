# Phase 04 — Identity, Bearer JWT, and authorization

**IDs:** SEC-01–SEC-04, SEC-07, SEC-08, SEC-10. **Runtime:** Java 21, Spring Boot 4.1.1.  
This isolated API module uses Spring Security Resource Server and Nimbus JWT support. It has no live identity provider and requires no user database.

## Configure and build

Copy `.env.example` to `.env`, generate a local-only HMAC secret (at least 32 random bytes), and put it in that ignored `.env`; never commit it:

```powershell
openssl rand -base64 32
```

Paste the output after `JWT_HMAC_SECRET_BASE64=` in `.env`, then run from this module directory:

```bash
mvn -DskipTests package
mvn spring-boot:run
```

The secret is only for this local single-module exercise; do not reuse it or paste it into source control.

## Exercises

| ID | Objective / TODO | Acceptance |
|---|---|---|
| SEC-01 | Registration/login/logout/password-change lifecycle using `PasswordCredentialService` and BCrypt. Add validation, persistence boundary, and session/token invalidation. | Stored value is one-way encoded; wrong password is denied; change/logout invalidates the documented credential/session. |
| SEC-02 | Complete issuer/audience/scope validation and short access-token issuance in `AccessTokenIssuer`. | Use [Bearer JWT contract](../../../docs/fullstack-junior-practice/contracts/SEC-02-bearer-jwt.md); check no token, valid, expired, altered, and wrong-scope behavior. |
| SEC-03 | Add role/scope rules and method-level authorization; distinguish 401 from 403. | A valid user token cannot call the admin route; an admin token can. |
| SEC-04 | Apply `ResourceOwnershipPolicy` to every resource read/write path. | A user cannot infer or modify another user's resource through a direct API call. |
| SEC-07 | Extension: refresh token rotation, reuse detection, revocation, expiry, logout. | Replayed previous refresh token is rejected; no token is stored in browser local/session storage. |
| SEC-08 | Review all configuration and generated frontend assets for secret exposure. | Missing local JWT key fails startup; no key/secret is committed or exposed in a response. |
| SEC-10 | Extension: account lock/unlock lifecycle and safe login denial. | Locked user cannot authenticate; unlock requires an authorized operator; no lock-state leak beyond the chosen error policy. |

## Code work areas

- `config/ApiSecurityConfiguration.java`: stateless bearer-only chain; CSRF disable applies only to that chain.
- `config/JwtKeyConfiguration.java`: secret comes from environment and crypto stays in Spring/Nimbus libraries.
- `service/AccessTokenIssuer.java`: claims/issuer/audience/expiry TODO.
- `service/PasswordCredentialService.java`: BCrypt baseline; add policy without logging raw secrets.
- `service/ResourceOwnershipPolicy.java`: enforce backend ownership checks at every use case.

For session cookies and CSRF use the separate [browser-session module](../phase-04-browser-session/README.md), not this stateless API chain.

## Hints and references

1. Authentication establishes who the caller is; authorization decides what that principal may do.
2. Spring Resource Server validates bearer tokens; keep role/ownership decisions in Java.
3. Use different keys/secrets per environment and rotate them; never paste keys into source.

- [Spring Security Resource Server JWT](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Spring Security PasswordEncoder](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/password-encoder.html)
- [OWASP Session Management](https://cheatsheetseries.owasp.org/cheatsheets/Session_Management_Cheat_Sheet.html)
