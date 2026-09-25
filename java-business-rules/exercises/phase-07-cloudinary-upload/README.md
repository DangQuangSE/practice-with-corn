# Phase 07 — Cloudinary signed-upload adapter

**ID:** INT-03. **Tier:** Extension. **Difficulty:** Medium. **Runtime:** Java 21, Spring Boot 4.1.1.

The signer is intentionally incomplete and requires no Cloudinary account to compile. It builds on [FILE-01](../phase-05-safe-upload/README.md): validate bounded content before issuing a signature. Read the provider's current official signing guidance before implementing canonical parameters.

## Required behavior

- API secret stays on Java server; browser receives only short-lived upload parameters.
- Restrict folder/public ID, allowed formats, maximum bytes, and upload timestamp server-side.
- Verify the provider result on the backend before trusting metadata; handle provider error, cancellation, and retry states.
- Use a fake signer/provider response for deterministic local work; use a vendor sandbox only optionally.
- On the FE, implement upload progress, validation error, provider error, cancel/retry, and success states. Do not accept a client-supplied URL as trusted proof of upload.

## Verify

Run `mvn -DskipTests package`. Configure only local ignored environment values if you later use a sandbox; never put `CLOUDINARY_API_SECRET` in a `NEXT_PUBLIC_` variable.

- [Cloudinary signed client-side uploads](https://cloudinary.com/documentation/client_side_uploading)
