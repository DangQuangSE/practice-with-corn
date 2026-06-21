-- Xóa bảng cũ theo thứ tự để tránh lỗi ràng buộc khóa ngoại
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS accounts;
-- 1. Tạo bảng Danh sách tài khoản (10,000 tài khoản)
-- Thay IDENTITY(1,1) cho SERIAL
CREATE TABLE accounts (
    account_id VARCHAR(50) PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE products (
    product_id INT IDENTITY(1,1) PRIMARY KEY, -- <--- SQL SERVER AUTO INCREMENT
    product_name VARCHAR(150) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(12, 2) NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE orders (
    order_id VARCHAR(50) PRIMARY KEY,
    account_id VARCHAR(50) NOT NULL FOREIGN KEY REFERENCES accounts(account_id),
    product_id INT NOT NULL FOREIGN KEY REFERENCES products(product_id),
    quantity INT NOT NULL,
    total_price DECIMAL(15, 2) NOT NULL,
    order_status VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE transactions (
    transaction_id VARCHAR(50) PRIMARY KEY,
    order_id VARCHAR(50) NOT NULL FOREIGN KEY REFERENCES orders(order_id),
    account_id VARCHAR(50) NOT NULL FOREIGN KEY REFERENCES accounts(account_id),
    amount DECIMAL(15, 2) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    location VARCHAR(100) NOT NULL,
    device_id VARCHAR(50) NOT NULL,
    timestamp DATETIME NOT NULL
)

-- 1. BƠM DATA CHO ACCOUNTS (10,000 dòng)
PRINT 'Inserting Accounts...';
WITH E1(N) AS (SELECT 1 FROM (VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1)) t(N)), -- 10
     E2(N) AS (SELECT 1 FROM E1 a CROSS JOIN E1 b), -- 100
     E4(N) AS (SELECT 1 FROM E2 a CROSS JOIN E2 b), -- 10,000
     Tcte(Id) AS (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) FROM E4)
INSERT INTO accounts (account_id, customer_name, account_type, balance, created_at)
SELECT 
    'ACC-' + CAST(Id AS VARCHAR(10)) AS account_id,
    'Customer Name ' + CAST(Id AS VARCHAR(10)) AS customer_name,
    CASE ABS(CHECKSUM(NewId())) % 3 
        WHEN 0 THEN 'SAVINGS' WHEN 1 THEN 'CHECKING' ELSE 'CREDIT' 
    END AS account_type,
    CAST(ABS(CHECKSUM(NewId())) % 50000000 AS DECIMAL(15,2)) AS balance,
    DATEADD(day, -(ABS(CHECKSUM(NewId())) % 365), GETDATE()) AS created_at
FROM Tcte;
GO

-- 2. BƠM DATA CHO PRODUCTS (500 dòng)
PRINT 'Inserting Products...';
WITH E1(N) AS (SELECT 1 FROM (VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1)) t(N)),
     E3(N) AS (SELECT 1 FROM E1 a CROSS JOIN E1 b CROSS JOIN E1 c), -- 1,000
     Tcte(Id) AS (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) FROM E3)
INSERT INTO products (product_name, category, price, stock_quantity)
SELECT TOP 500
    'Product Model ' + CAST(Id AS VARCHAR(10)) AS product_name,
    CASE ABS(CHECKSUM(NewId())) % 5 
        WHEN 0 THEN 'ELECTRONICS' WHEN 1 THEN 'CLOTHING' WHEN 2 THEN 'HOME' WHEN 3 THEN 'BOOKS' ELSE 'SPORTS' 
    END AS category,
    CAST((ABS(CHECKSUM(NewId())) % 1500000 + 5000) AS DECIMAL(12,2)) AS price,
    ABS(CHECKSUM(NewId())) % 1000 + 10 AS stock_quantity
FROM Tcte;
GO

-- 3. BƠM DATA CHO ORDERS (1,000,000 dòng)
PRINT 'Inserting 1M Orders...';
WITH E1(N) AS (SELECT 1 FROM (VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1)) t(N)),
     E2(N) AS (SELECT 1 FROM E1 a CROSS JOIN E1 b), -- 100
     E3(N) AS (SELECT 1 FROM E2 a CROSS JOIN E1 b), -- 1,000
     E6(N) AS (SELECT 1 FROM E3 a CROSS JOIN E3 b), -- 1,000,000
     Tcte(Id) AS (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) FROM E6)
INSERT INTO orders (order_id, account_id, product_id, quantity, total_price, order_status, created_at)
SELECT 
    'ORD-' + CAST(Id AS VARCHAR(20)) AS order_id,
    'ACC-' + CAST((ABS(CHECKSUM(NewId())) % 10000 + 1) AS VARCHAR(10)) AS account_id,
    (ABS(CHECKSUM(NewId())) % 500 + 1) AS product_id,
    (ABS(CHECKSUM(NewId())) % 5 + 1) AS quantity,
    0 AS total_price, -- Sẽ được cập nhật chính xác theo giá sản phẩm ở bước dưới để tránh tốn RAM tính toán gộp
    CASE ABS(CHECKSUM(NewId())) % 4 
        WHEN 0 THEN 'COMPLETED' WHEN 1 THEN 'PENDING' WHEN 2 THEN 'CANCELLED' ELSE 'REFUNDED' 
    END AS order_status,
    DATEADD(day, -(ABS(CHECKSUM(NewId())) % 90), GETDATE()) AS created_at
FROM Tcte;
GO

-- Cập nhật lại total_price chuẩn theo giá của bảng Products
PRINT 'Syncing Order Prices...';
UPDATE o
SET o.total_price = p.price * o.quantity
FROM orders o
JOIN products p ON o.product_id = p.product_id;
GO

-- 4. BƠM DATA CHO TRANSACTIONS (1,000,000 dòng kết nối trực tiếp với Orders)
PRINT 'Inserting 1M Transactions linked to Orders...';
INSERT INTO transactions (transaction_id, order_id, account_id, amount, transaction_type, location, device_id, timestamp)
SELECT 
    'TXN-' + SUBSTRING(order_id, 5, 20) AS transaction_id,
    order_id,
    account_id,
    total_price AS amount,
    CASE order_status 
        WHEN 'COMPLETED' THEN 'PAYMENT'
        WHEN 'REFUNDED' THEN 'REFUND'
        ELSE CASE ABS(CHECKSUM(NewId())) % 3 WHEN 0 THEN 'TRANSFER' WHEN 1 THEN 'WITHDRAWAL' ELSE 'DEPOSIT' END
    END AS transaction_type,
    CASE ABS(CHECKSUM(NewId())) % 5
        WHEN 0 THEN 'Ho Chi Minh City' WHEN 1 THEN 'Ha Noi' WHEN 2 THEN 'Da Nang' WHEN 3 THEN 'Can Tho' ELSE 'Hai Phong'
    END AS location,
    'DEV-' + CASE ABS(CHECKSUM(NewId())) % 4 WHEN 0 THEN 'IPHONE' WHEN 1 THEN 'SAMSUNG' WHEN 2 THEN 'WEB' ELSE 'XIAOMI' END + CAST(ABS(CHECKSUM(NewId())) % 100 AS VARCHAR(3)) AS device_id,
    DATEADD(minute, ABS(CHECKSUM(NewId())) % 10, created_at) AS timestamp
FROM orders;
GO

PRINT 'Done! All data seeded successfully.';