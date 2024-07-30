-- Create table for Offer entity
CREATE TABLE offers
(
    id                   UUID PRIMARY KEY,
    created_at           TIMESTAMP        NOT NULL,
    updated_at           TIMESTAMP        NOT NULL,
    deleted_at           TIMESTAMP,
    title                VARCHAR(255)     NOT NULL CHECK (title <> ''),
    phone_number         VARCHAR(255)     NOT NULL CHECK (phone_number <> ''),
    description          TEXT             NOT NULL CHECK (description <> ''),
    website_url          VARCHAR(255),
    price                DOUBLE PRECISION NOT NULL CHECK (price >= 1),
    bulgarian            BOOLEAN          NOT NULL,
    auto_update          BOOLEAN          NOT NULL DEFAULT FALSE,
    offer_state          VARCHAR(255)     NOT NULL,
    offer_sale_type      VARCHAR(255)     NOT NULL,
    offer_type           VARCHAR(255)     NOT NULL DEFAULT 'BASIC',
    city_id              UUID             NOT NULL,
    subcategory_id       UUID             NOT NULL,
    main_picture_id      UUID             NOT NULL,
    owner_id             UUID             NOT NULL,
    manufacture_year     INT,
    model                VARCHAR(255),
    power_supply_voltage DOUBLE PRECISION CHECK (power_supply_voltage >= 0.1),
    fuel_type            VARCHAR(255),
    horse_power          DOUBLE PRECISION CHECK (horse_power >= 0.1),
    consumption          VARCHAR(255),
    output_power         DOUBLE PRECISION CHECK (output_power >= 0.1),
    productivity         VARCHAR(255),
    capacity             DOUBLE PRECISION CHECK (capacity >= 0.1),
    min_revolutions      DOUBLE PRECISION CHECK (min_revolutions >= 0.1),
    nominal_revolutions  DOUBLE PRECISION CHECK (nominal_revolutions >= 0.1),
    max_revolutions      DOUBLE PRECISION CHECK (max_revolutions >= 0.1),
    dimensions           VARCHAR(255),
    own_weight           DOUBLE PRECISION CHECK (own_weight >= 0.1),
    work_moves           VARCHAR(255),
    CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES cities (id),
    CONSTRAINT fk_subcategory FOREIGN KEY (subcategory_id) REFERENCES subcategories (id),
    CONSTRAINT fk_main_picture FOREIGN KEY (main_picture_id) REFERENCES files (id),
    CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT offers_offer_sale_type_check CHECK (((offer_sale_type)::text = ANY ((ARRAY['SALE'::character varying, 'FOR_RENT'::character varying, 'SERVICES'::character varying])::text[]))),
    CONSTRAINT offers_offer_state_check CHECK (((offer_state)::text = ANY ((ARRAY['NEW'::character varying, 'NEW_IMPORTATION'::character varying, 'USED'::character varying])::text[]))),
    CONSTRAINT offers_offer_type_check CHECK (((offer_type)::text = ANY ((ARRAY['BASIC'::character varying, 'VIP'::character varying, 'TOP'::character varying])::text[])))
);

-- Create join table for Offer and File (many-to-many relationship)
CREATE TABLE offers_pictures
(
    offer_id UUID NOT NULL,
    file_id  UUID NOT NULL,
    PRIMARY KEY (offer_id, file_id),
    CONSTRAINT fk_offer FOREIGN KEY (offer_id) REFERENCES offers (id) ON DELETE CASCADE,
    CONSTRAINT fk_file FOREIGN KEY (file_id) REFERENCES files (id) ON DELETE CASCADE
);

-- Create GIN index for full-text search on the offer title
CREATE INDEX offers_title ON offers USING gin(to_tsvector('simple', title));
