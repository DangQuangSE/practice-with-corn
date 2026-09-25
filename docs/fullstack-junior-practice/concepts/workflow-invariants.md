# Workflow invariants, transactions, and idempotency

Relevant exercises: FLOW-01, FLOW-03–FLOW-09, FLOW-11, BE-14–BE-15.

## Start with invariants

An invariant is a condition that must remain true before and after an operation. Examples: stock never becomes negative; a cancelled order cannot become fulfilled; a duplicate profile key cannot be claimed by two accounts; a report amount is calculated from persisted order data rather than a browser total. Write the invariant and its rejected boundary cases before implementing endpoints.

## Keep related state changes atomic

Checkout often changes both order state and inventory. Put those writes in one database transaction, validate the transition on the server, and make failure roll back both changes. Do not reserve inventory in one transaction and then assume a later request will always succeed.

## Distinguish optimistic locking from idempotency

- Optimistic locking (`@Version`, BE-14) detects that another writer changed a row since it was read. Report a conflict and let the caller reload or retry deliberately.
- Idempotency (BE-15) makes a repeated client command safe by storing a stable key, request fingerprint, and prior result. Reusing a key with different input must conflict.
- Neither technique means a distributed message or payment happens “exactly once.” Design retries around durable state and observable outcomes.

## Model explicit state transitions

Use named states and an allowlist of transitions rather than arbitrary status updates. Validate authorization and current state together; record actor/time/target for audit (FLOW-11), while excluding credentials, tokens, and raw sensitive payloads.

## Bound untrusted input

Uploads and CSV imports need byte/row limits before expensive processing. MIME headers, filenames, CSV cells, query parameters, and browser-submitted prices are untrusted. Return narrow safe errors and keep parsing/storage behind replaceable adapters.

## Practice checks

- What remains committed if stock reservation fails after order validation?
- What should happen if the same cancel request is submitted twice?
- How does an optimistic conflict differ from a duplicate idempotent retry?
- What should the UI show while the server has not confirmed a state change?
