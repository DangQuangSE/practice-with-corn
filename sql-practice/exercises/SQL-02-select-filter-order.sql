-- SQL-02 · Core / Easy · SELECT, WHERE, ORDER BY, LIMIT/OFFSET, NULL
-- TODO: list the 3 cheapest in-stock products in a requested category.
-- Return product_id, product_name, price; tie-break by product_id and make row order deterministic.
SELECT product_id, product_name, price
FROM products
WHERE stock_quantity > 0
  -- TODO: filter the requested category
ORDER BY price ASC, product_id ASC
LIMIT 3;

-- TODO: add a second query that distinguishes `IS NULL` from `= NULL`.
