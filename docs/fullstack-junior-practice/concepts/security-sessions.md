# Authentication, authorization, and browser sessions

Authentication answers “who is this caller?” Authorization answers “may this caller perform this action on this resource?” A route guard can improve user experience, but the Java API must enforce role/permission and ownership checks on every protected operation.

For a browser flow, a server-owned session cookie should be HttpOnly, Secure, and SameSite-configured. Because the browser sends cookies automatically, unsafe requests need CSRF defense; a frontend must send a CSRF token while keeping the session cookie inaccessible to JavaScript. Credentialed CORS should name exact trusted origins.

Bearer JWT is a different model: the client explicitly sends `Authorization: Bearer ...`; a maintained resource-server library validates signature and claims. It does not become safer merely because it is a JWT, and browser storage in local/session storage creates a theft target. Keep the browser-session and stateless API exercises separate.

Passwords are not encrypted for later recovery; use a maintained `PasswordEncoder` such as Spring Security's BCrypt support. Do not log raw credentials or token values. For UI output, render untrusted strings as text and avoid raw HTML injection.

## Practice

| IDs | Apply |
|---|---|
| [SEC-01–SEC-04, SEC-07, SEC-10](../../../java-business-rules/exercises/phase-04-identity-security/README.md) | Password lifecycle, bearer JWT, permissions, ownership, refresh rotation, account lock |
| [SEC-05–SEC-06](../../../java-business-rules/exercises/phase-04-browser-session/README.md) | Cookie session, CSRF, CORS, XSS and trust boundary |
| [FE-08](../../../nextjs-business-rules/exercises/phase-04-access-states/README.md) | Session-aware UI states; Java remains authoritative |
| [TEST-04](../../../plans/fullstack-junior-practice/inventory-map.md#c-authentication-authorization-security) | Allow/deny and ownership verification |

## Further reading

- [Spring Security JWT Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html)
- [Spring Security PasswordEncoder](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/password-encoder.html)
- [Spring Security CSRF](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html)
- [OWASP Session Management](https://cheatsheetseries.owasp.org/cheatsheets/Session_Management_Cheat_Sheet.html)
- [OWASP CSRF Prevention](https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html)
