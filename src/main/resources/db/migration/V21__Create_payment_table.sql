-- Create table for Payment entity
CREATE TABLE payments
(
    id                  UUID PRIMARY KEY,
    created_at          TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP NOT NULL,
    deleted_at          TIMESTAMP,
    description         VARCHAR(255),
    currency            VARCHAR(255),
    status              VARCHAR(255),
    payment_method_type VARCHAR(255),
    metadata            TEXT,
    amount              BIGINT,
    amount_received     BIGINT,
    customer_id         VARCHAR(255),
    customer_email      VARCHAR(255),
    customer_name       VARCHAR(255),
    payment_provider    VARCHAR(50),
    CONSTRAINT payment_provider_check CHECK (((payment_provider)::text = ANY
                                              ((ARRAY ['STRIPE'::character varying, 'SMS'::character varying])::text[])))

);