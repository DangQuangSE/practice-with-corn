-- Small, deterministic, synthetic fixture. Re-running does not duplicate rows.
INSERT INTO accounts (account_id, customer_name, account_type, balance, created_at) VALUES
    ('ACC-0001', 'Customer One',   'SAVINGS',  1200.00, now() - interval '220 days'),
    ('ACC-0002', 'Customer Two',   'CHECKING',  850.50, now() - interval '180 days'),
    ('ACC-0003', 'Customer Three', 'CREDIT',   2500.00, now() - interval '120 days'),
    ('ACC-0004', 'Customer Four',  'SAVINGS',   400.00, now() - interval '60 days'),
    ('ACC-0005', 'Customer Five',  'CHECKING',  9900.00, now() - interval '30 days')
ON CONFLICT (account_id) DO NOTHING;

INSERT INTO products (product_name, category, price, stock_quantity) VALUES
    ('Notebook', 'BOOKS',       4.50,  80),
    ('Desk Lamp', 'HOME',      24.00,  25),
    ('Keyboard', 'ELECTRONICS', 49.99, 18),
    ('Water Bottle', 'SPORTS', 12.00,  42),
    ('T-Shirt', 'CLOTHING',    15.00,  60),
    ('Pen Set', 'BOOKS',        8.00, 100)
ON CONFLICT (product_name) DO NOTHING;

INSERT INTO orders (order_id, account_id, total_price, order_status, created_at) VALUES
    ('ORD-0001', 'ACC-0001',  9.00, 'COMPLETED', now() - interval '2 days'),
    ('ORD-0002', 'ACC-0001', 49.99, 'COMPLETED', now() - interval '9 days'),
    ('ORD-0003', 'ACC-0001', 24.00, 'PENDING',   now() - interval '40 days'),
    ('ORD-0004', 'ACC-0002', 30.00, 'COMPLETED', now() - interval '1 day'),
    ('ORD-0005', 'ACC-0002', 15.00, 'CANCELLED', now() - interval '18 days'),
    ('ORD-0006', 'ACC-0003', 49.99, 'COMPLETED', now() - interval '3 days'),
    ('ORD-0007', 'ACC-0003', 24.00, 'REFUNDED',  now() - interval '75 days'),
    ('ORD-0008', 'ACC-0004', 36.00, 'COMPLETED', now() - interval '12 days'),
    ('ORD-0009', 'ACC-0004', 12.00, 'PENDING',   now() - interval '5 days'),
    ('ORD-0010', 'ACC-0005', 16.00, 'COMPLETED', now() - interval '22 days')
ON CONFLICT (order_id) DO NOTHING;

INSERT INTO order_lines (order_id, product_id, quantity, unit_price)
SELECT seed.order_id, product.product_id, seed.quantity, product.price
FROM (VALUES
    ('ORD-0001', 'Notebook', 2),
    ('ORD-0002', 'Keyboard', 1),
    ('ORD-0003', 'Desk Lamp', 1),
    ('ORD-0004', 'T-Shirt', 2),
    ('ORD-0005', 'T-Shirt', 1),
    ('ORD-0006', 'Keyboard', 1),
    ('ORD-0007', 'Desk Lamp', 1),
    ('ORD-0008', 'Pen Set',  2),
    ('ORD-0009', 'Water Bottle', 1),
    ('ORD-0010', 'Pen Set',  2)
) AS seed(order_id, product_name, quantity)
JOIN products AS product USING (product_name)
ON CONFLICT (order_id, product_id) DO NOTHING;

INSERT INTO transactions (transaction_id, order_id, account_id, amount, transaction_type, location, device_id, occurred_at) VALUES
    ('TXN-0001', 'ORD-0001', 'ACC-0001',  9.00, 'PAYMENT', 'Ha Noi', 'DEV-WEB-01', now() - interval '2 days'),
    ('TXN-0002', 'ORD-0002', 'ACC-0001', 49.99, 'PAYMENT', 'Da Nang', 'DEV-MOBILE-02', now() - interval '9 days'),
    ('TXN-0003', 'ORD-0004', 'ACC-0002', 30.00, 'PAYMENT', 'Can Tho', 'DEV-WEB-01', now() - interval '1 day'),
    ('TXN-0004', 'ORD-0006', 'ACC-0003', 49.99, 'PAYMENT', 'Ha Noi', 'DEV-MOBILE-03', now() - interval '3 days'),
    ('TXN-0005', 'ORD-0007', 'ACC-0003', 24.00, 'REFUND',  'Ha Noi', 'DEV-WEB-02', now() - interval '70 days'),
    ('TXN-0006', 'ORD-0008', 'ACC-0004', 35.00, 'PAYMENT', 'Da Nang', 'DEV-MOBILE-01', now() - interval '12 days'),
    ('TXN-0007', 'ORD-0010', 'ACC-0005', 16.00, 'PAYMENT', 'Can Tho', 'DEV-WEB-03', now() - interval '22 days')
ON CONFLICT (transaction_id) DO NOTHING;

-- Synthetic velocity pattern for the window-function challenge (10-minute span).
INSERT INTO transactions (transaction_id, order_id, account_id, amount, transaction_type, location, device_id, occurred_at) VALUES
    ('TXN-VELOCITY-01', 'ORD-0003', 'ACC-0001', 5.00, 'TRANSFER', 'Synthetic', 'DEV-TEST', now() - interval '13 minutes'),
    ('TXN-VELOCITY-02', 'ORD-0003', 'ACC-0001', 5.00, 'TRANSFER', 'Synthetic', 'DEV-TEST', now() - interval '8 minutes'),
    ('TXN-VELOCITY-03', 'ORD-0003', 'ACC-0001', 5.00, 'TRANSFER', 'Synthetic', 'DEV-TEST', now() - interval '3 minutes')
ON CONFLICT (transaction_id) DO NOTHING;
