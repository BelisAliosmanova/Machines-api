-- Create table for Region entity
CREATE TABLE regions
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    name       VARCHAR(255) NOT NULL,
    country_id UUID         NOT NULL,
    CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES countries (id),
    CONSTRAINT chk_region_name CHECK (name <> ''),
    CONSTRAINT regions_name_not_blank CHECK (name IS NOT NULL AND name <> '')
);
