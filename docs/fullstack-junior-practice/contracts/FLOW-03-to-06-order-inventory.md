# FLOW-03–FLOW-06 — Catalog, cart, order, inventory

**Pair:** Java owns domain state and persistence; Next.js owns catalog/cart/order screens. Start either side against synthetic fixtures/mock responses.

## API outline

- `GET /api/products?query=&page=&size=`: bounded search, stable sort, no soft-deleted rows.
- `POST /api/orders`: `{ lines: [{ productId, quantity }], couponCode? }`; server recalculates prices and discount.
- `POST /api/orders/{id}/cancel`: server validates transition and releases reserved stock once.
- Success uses the shared envelope; validation is `400`, missing resource `404`, illegal transition/insufficient stock `409`, unauthenticated/forbidden `401`/`403`.
- Money is decimal (`numeric`/`BigDecimal`), quantities are positive bounded integers, and client-submitted price/total is ignored.
- Checkout's order write and inventory reservation are one transaction. Retry behavior must use an idempotency key and must not claim exactly-once delivery.
- FE mock cases: empty catalog, invalid coupon, failed stock reservation, repeated cancel, stale version conflict.
