-- Create table for Product entity
CREATE TABLE products (
     id UUID PRIMARY KEY,
     created_at TIMESTAMP    NOT NULL,
     updated_at TIMESTAMP    NOT NULL,
     deleted_at TIMESTAMP,
     name VARCHAR(255) NOT NULL,
     checkout_id VARCHAR(255) NOT NULL,
     currency VARCHAR(10) NOT NULL,
     unit_amount_decimal NUMERIC(19, 2)  NOT NULL,
     CONSTRAINT products_name_not_blank CHECK (name IS NOT NULL AND name <> ''),
     CONSTRAINT products_checkout_id_not_blank CHECK (checkout_id IS NOT NULL AND checkout_id <> ''),
     CONSTRAINT products_currency_not_blank CHECK (currency IS NOT NULL AND currency <> ''),
     CONSTRAINT products_unit_amount_decimal_min CHECK (unit_amount_decimal >= 0.0)
);