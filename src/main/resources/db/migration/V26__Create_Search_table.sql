-- Create table for Search entity
CREATE TABLE searches
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    text       VARCHAR(255) NOT NULL
);