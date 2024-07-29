-- Create table for Category entity
CREATE TABLE categories
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT chk_name CHECK (name <> ''),
    CONSTRAINT categories_name_not_blank CHECK (name IS NOT NULL AND name <> '')
);
