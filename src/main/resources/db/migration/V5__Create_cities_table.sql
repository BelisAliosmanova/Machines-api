-- Create table for City entity
CREATE TABLE cities
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    name       VARCHAR(255) NOT NULL,
    region_id  UUID         NOT NULL,
    CONSTRAINT fk_region FOREIGN KEY (region_id) REFERENCES regions (id),
    CONSTRAINT chk_city_name CHECK (name <> ''),
    CONSTRAINT cities_name_not_blank CHECK (name IS NOT NULL AND name <> '')
);
