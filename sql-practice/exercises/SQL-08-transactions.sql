-- SQL-08 · Core / Medium · transactions and basic isolation/concurrency
-- This is a manual lab; do not open two sessions against production data.
BEGIN;

-- TODO: model a two-step balance transfer between two synthetic accounts.
-- Require both updates to succeed or neither to persist; inspect the rows before commit.
-- Extension: compare behavior in two local sessions under READ COMMITTED and REPEATABLE READ.

ROLLBACK; -- Safe default. Change only after reviewing the rows and the local database target.
