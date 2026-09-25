# SEC-02 — Local Bearer JWT exercise contract

This is a separate stateless API exercise, not the browser session used by SEC-05/06.

## Contract

- API clients send `Authorization: Bearer <access-token>`.
- Token issuer/decoder use Spring Security/Nimbus APIs; do not write signature verification, token parsing, or crypto by hand.
- Validate signature, expiry/not-before, issuer and audience. Use a short-lived access token and documented scopes/roles.
- 401 means absent, malformed, invalid-signature, or expired token. 403 means a valid principal lacks the required authority.
- Signing material is generated locally and passed through an ignored `.env`; never commit a key or use a production credential.
- This local single-module exercise uses HS256 for simplicity. A multi-service deployment should use an issuer-managed asymmetric key/JWK set rather than sharing one secret across services.
- Do not persist browser tokens in localStorage/sessionStorage. Use `curl.exe` or an API client with a local-only environment for the standalone exercise.

## Verification cases

1. No token → 401.
2. Valid token, required scope → success.
3. Valid token, insufficient scope → 403.
4. Expired or altered token → 401.
5. Token with wrong issuer/audience → 401 after those validators are configured.
