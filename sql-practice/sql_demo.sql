-- PostgreSQL psql entry point for the shared learning schema and deterministic seed.
-- Run from any working directory: psql -v ON_ERROR_STOP=1 -f sql-practice/sql_demo.sql
\ir schema.sql
\ir seed.sql
\echo 'Practice schema and small synthetic fixture are ready.'
