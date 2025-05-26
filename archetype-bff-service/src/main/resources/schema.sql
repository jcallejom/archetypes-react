CREATE TABLE purchase_order (
    id VARCHAR(50) PRIMARY KEY,
    user_id INT,
    product_id INT,
    price NUMBER(10, 2),
    status VARCHAR(50)
);
CREATE TABLE client_order (
    id VARCHAR(50) PRIMARY KEY,
    client_id VARCHAR(12),
    user_id VARCHAR(12),
    card_id VARCHAR(12),
    status VARCHAR(50)
);
