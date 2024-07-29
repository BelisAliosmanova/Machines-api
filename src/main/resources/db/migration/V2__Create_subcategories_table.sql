-- Create table for Subcategory entity
CREATE TABLE subcategories
(
    id          UUID PRIMARY KEY,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    deleted_at  TIMESTAMP,
    name        VARCHAR(255) NOT NULL,
    category_id UUID         NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT chk_name CHECK (name <> ''),
    CONSTRAINT subcategories_name_not_blank CHECK (name IS NOT NULL AND name <> '')
);
