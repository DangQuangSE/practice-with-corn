-- SQL-09 · Extension / Challenge · index choice and PostgreSQL plan reading
-- Use the optional larger seed only if you have enough local disk/time.
-- TODO: compare the recent-completed-orders query before/after an index on status + created_at.
EXPLAIN (ANALYZE, BUFFERS)
SELECT order_id, account_id, total_price, created_at
FROM orders
WHERE order_status = 'COMPLETED'
  AND created_at >= now() - interval '30 days'
ORDER BY created_at DESC;

-- TODO: record estimated vs actual rows, buffers, and whether the index helped this data shape.
-- Never require an Index Scan or fixed millisecond threshold; PostgreSQL may correctly choose a sequential scan.
