-- SQL-03 · Core / Easy · INSERT/UPDATE/DELETE and constraints
-- Try the mutation inside a transaction and inspect RETURNING before deciding to commit.
BEGIN;

-- TODO: reduce stock for one known product, only if enough stock remains.
-- TODO: use RETURNING to show the changed row; observe the check constraint on invalid stock.

ROLLBACK; -- Keep rollback until you intentionally review and change this line.
