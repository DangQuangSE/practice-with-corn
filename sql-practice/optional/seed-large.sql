-- OPT-IN CHALLENGE: append 100,000 synthetic orders to an already seeded database.
-- This may take noticeable time and disk space. Run only after reviewing the query.
-- The fixed prefix makes each run distinct; remove only these named rows to reset.
BEGIN;
INSERT INTO orders (order_id, account_id, total_price, order_status, created_at)
SELECT
    'ORD-LARGE-' || lpad(series.n::text, 6, '0'),
    'ACC-' || lpad(((series.n - 1) % 5 + 1)::text, 4, '0'),
    round(product.price * ((series.n - 1) % 4 + 1), 2),
    (ARRAY['COMPLETED', 'PENDING', 'CANCELLED', 'REFUNDED'])[((series.n - 1) % 4 + 1)::integer],
    now() - ((series.n - 1) % 90) * interval '1 day'
FROM generate_series(1, 100000) AS series(n)
JOIN products AS product
  ON product.product_name = (ARRAY['Notebook', 'Desk Lamp', 'Keyboard', 'Water Bottle', 'T-Shirt', 'Pen Set'])[((series.n - 1) % 6 + 1)::integer]
ON CONFLICT (order_id) DO NOTHING;

INSERT INTO order_lines (order_id, product_id, quantity, unit_price)
SELECT order_row.order_id, product.product_id,
       ((series.n - 1) % 4 + 1)::integer, product.price
FROM generate_series(1, 100000) AS series(n)
JOIN products AS product
  ON product.product_name = (ARRAY['Notebook', 'Desk Lamp', 'Keyboard', 'Water Bottle', 'T-Shirt', 'Pen Set'])[((series.n - 1) % 6 + 1)::integer]
JOIN orders AS order_row ON order_row.order_id = 'ORD-LARGE-' || lpad(series.n::text, 6, '0')
ON CONFLICT (order_id, product_id) DO NOTHING;

INSERT INTO transactions (transaction_id, order_id, account_id, amount, transaction_type, location, device_id, occurred_at)
SELECT 'TXN-LARGE-' || lpad(order_row.order_id, 16, '0'),
       order_row.order_id, order_row.account_id, order_row.total_price,
       CASE order_row.order_status WHEN 'COMPLETED' THEN 'PAYMENT' WHEN 'REFUNDED' THEN 'REFUND' ELSE 'DEPOSIT' END,
       'Synthetic', 'DEV-LARGE', order_row.created_at
FROM orders AS order_row
WHERE order_row.order_id LIKE 'ORD-LARGE-%'
ON CONFLICT (transaction_id) DO NOTHING;
COMMIT;
