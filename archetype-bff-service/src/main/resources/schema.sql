CREATE TABLE purchase_order (
    id NUMBER PRIMARY KEY,
    user_id INT,
    product_id INT,
    price NUMBER(10, 2),
    status VARCHAR(50)
);