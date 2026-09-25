-- psql entry point. Flyway migration is the canonical schema definition.
SELECT to_regclass('public.accounts') IS NULL AS create_schema \gset
\if :create_schema
\ir migrations/V1__initial_schema.sql
\else
\echo 'Schema already exists; leaving it unchanged.'
\endif
