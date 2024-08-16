-- Create table for Advertisement entity
CREATE TABLE advertisements
(
    id            UUID PRIMARY KEY,
    created_at    TIMESTAMP        NOT NULL,
    updated_at    TIMESTAMP        NOT NULL,
    deleted_at    TIMESTAMP,
    title         VARCHAR(255)     NOT NULL CHECK (title <> ''),
    target_url    VARCHAR(255)     NOT NULL CHECK (target_url <> ''),
    position      VARCHAR(255)     NOT NULL CHECK (position IN ('Top', 'Center', 'Bottom', 'Gallery')),
    start_date    DATE             NOT NULL,
    end_date      DATE             NOT NULL,
    active        BOOLEAN          NOT NULL DEFAULT FALSE,
    CONSTRAINT advertisements_start_end_date_check CHECK (start_date <= end_date)
);

-- Create join table for Advertisement and File (many-to-many relationship)
CREATE TABLE advertisements_pictures
(
    advertisement_id UUID NOT NULL,
    file_id          UUID NOT NULL,
    PRIMARY KEY (advertisement_id, file_id),
    CONSTRAINT fk_advertisement FOREIGN KEY (advertisement_id) REFERENCES advertisements (id) ON DELETE CASCADE,
    CONSTRAINT fk_file FOREIGN KEY (file_id) REFERENCES files (id) ON DELETE CASCADE
);

-- Create GIN index for full-text search on the advertisement title
CREATE INDEX advertisements_title ON advertisements USING gin(to_tsvector('simple', title));
