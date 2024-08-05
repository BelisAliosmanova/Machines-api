-- Create table for Offer entity
CREATE TABLE companies
(
    id                   UUID PRIMARY KEY,
    created_at           TIMESTAMP        NOT NULL,
    updated_at           TIMESTAMP        NOT NULL,
    deleted_at           TIMESTAMP,
    name                 VARCHAR(255)     NOT NULL CHECK (name <> ''),
    address              VARCHAR(255)     NOT NULL CHECK (name <> ''),
    fax                  VARCHAR(255)     NOT NULL CHECK (name <> ''),
    eik                  VARCHAR(255)     NOT NULL CHECK (name <> ''),
    phone_number         VARCHAR(255)     NOT NULL CHECK (phone_number <> ''),
    description          TEXT             NOT NULL CHECK (description <> ''),
    website              VARCHAR(255),
    city_id              UUID             NOT NULL,
    main_picture_id      UUID             NOT NULL,
    owner_id             UUID             NOT NULL,
    CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES cities (id),
    CONSTRAINT fk_main_picture FOREIGN KEY (main_picture_id) REFERENCES files (id),
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- Create join table for Offer and File (many-to-many relationship)
CREATE TABLE companies_pictures
(
    company_id UUID NOT NULL,
    file_id  UUID NOT NULL,
    PRIMARY KEY (company_id, file_id),
    CONSTRAINT fk_company FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    CONSTRAINT fk_file FOREIGN KEY (file_id) REFERENCES files (id) ON DELETE CASCADE
);

-- Create GIN index for full-text search on the company name
CREATE INDEX companies_name ON companies USING gin(to_tsvector('simple', name));
