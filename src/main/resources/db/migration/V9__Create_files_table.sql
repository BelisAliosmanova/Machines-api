-- Create table for File entity
CREATE TABLE files
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    deleted_at TIMESTAMP,
    name       VARCHAR(255) NOT NULL,
    type       VARCHAR(255),
    path       VARCHAR(255) NOT NULL,
    size       BIGINT,
    CONSTRAINT files_name_not_blank CHECK (name IS NOT NULL AND name <> ''),
    CONSTRAINT files_path_not_blank CHECK (path IS NOT NULL AND path <> '')
);
