-- Converted challenge prompts from the previous SQL Server day1.sql.
-- Dialect: PostgreSQL. The original scenarios are preserved; absolute millisecond goals,
-- SQL Server plan names (Index Seek/Merge Join/Hash Match), PRINT, GO, and TOP are removed.
-- Complete the TODO query in exercises/SQL-12-legacy-challenges.sql.

-- CHALLENGE 1 — Recent completed orders (Easy)
-- Return completed orders created in the last 30 days, newest first, then highest value.
-- Compare EXPLAIN (ANALYZE, BUFFERS) before and after a suitable index on a larger fixture.

-- CHALLENGE 2 — Financial reconciliation (Medium)
-- Find completed orders whose total_price differs from a linked transaction amount.
-- Keep the join on stable keys; inspect the plan without requiring a particular join algorithm.

-- CHALLENGE 3 — Top three orders per account (Medium)
-- Return account_id, order_id, total_price, and a per-account rank.
-- Decide how ties should behave and make the final ordering explicit.

-- CHALLENGE 4 — Transaction velocity (Challenge)
-- Find accounts with at least three consecutive transactions occurring within a rolling
-- interval shorter than 15 minutes. Use window functions and explain tie handling.

-- CHALLENGE 5 — Product revenue contribution (Challenge)
-- Return quantity and revenue per product plus that product's share of category revenue.
-- Use a clearly named aggregate/CTE; performance claims must be measured, not assumed.
