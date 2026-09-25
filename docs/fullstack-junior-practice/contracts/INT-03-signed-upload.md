# INT-03 / FE-11 — Cloudinary signed upload

**Java owns:** FILE-01 validation, storage policy, API secret, short-lived signature, provider result verification. **Next.js owns:** local selection/progress/error UX and upload of harmless fixtures.

- Java returns only `cloudName`, `apiKey`, bounded `folder/publicId`, timestamp, and signature; never returns API secret.
- Client request is size/type bounded; provider upload result is not trusted until Java verifies its signature/metadata.
- Keep the signing payload aligned to the provider's current official specification; do not rely on a copied stale signature recipe.
- Client states: pending, progress, rejected, failed/network, cancelled, retry, server-verified success.
- Automated work uses fake signer/provider responses; provider sandbox is optional.

References: [Cloudinary client-side upload documentation](https://cloudinary.com/documentation/client_side_uploading).
