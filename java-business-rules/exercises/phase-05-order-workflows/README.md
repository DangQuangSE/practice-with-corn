# Phase 05 — Order and inventory workflows

**IDs:** FLOW-01, FLOW-03–FLOW-06, BE-14–BE-15. **Level:** Core, Easy–Medium. **Runtime:** Java 21, Spring Boot 4.1.1.

This is a small domain exercise, not a finished commerce application. Each policy can be implemented independently; the TODO methods intentionally fail until completed. No database or provider credential is required to compile it.

## Exercises

| ID | Practice | Acceptance focus |
|---|---|---|
| FLOW-01 | Normalize profile identifiers and handle duplicate conflicts without disclosing another account's data. | Equivalent identifiers follow one documented rule; duplicate conflicts map to a stable API error. |
| FLOW-03 | Add a catalog DTO/query service with bounded search, stable sorting, and soft-delete semantics. | Deleted products are not returned; paging has a maximum size; entity fields do not leak. |
| FLOW-04 | Complete `DiscountPolicy` and a cart total using `BigDecimal`. | Invalid/expired/minimum-not-met coupons and rounding boundaries are explicit. |
| FLOW-05 | Complete `OrderTransitionPolicy`. | Only allowed state transitions succeed; repeated/illegal transitions are deterministic. |
| FLOW-06 | Complete `InventoryReservationService` and add persistence/repository behavior. | Insufficient inventory leaves order and stock unchanged; cancellation releases exactly once. |
| BE-14 | Use the entity `@Version` column to handle concurrent inventory changes. | A stale write becomes a documented conflict/reload path, not silent overselling. |
| BE-15 | Persist idempotency key + request fingerprint + result for retryable commands. | Same key/same request replays a result; same key/different request conflicts. Do not claim exactly-once delivery. |

## Work order and checks

1. Read the matching briefs in [`docs/fullstack-junior-practice/contracts`](../../../docs/fullstack-junior-practice/contracts/README.md).
2. Implement one policy at a time; introduce repositories only for exercises that need durable state.
3. Add deterministic tests for boundary and duplicate/concurrent cases when studying the testing track.
4. Run `mvn -DskipTests package` from this directory for a compile/package check.

No real payment or shared state with another exercise is part of this module.
