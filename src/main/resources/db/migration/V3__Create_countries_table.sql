-- Create table for Country entity
CREATE TABLE countries
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT chk_country_name CHECK (name <> ''),
    CONSTRAINT countries_name_not_blank CHECK (name IS NOT NULL AND name <> '')
);
