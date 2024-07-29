-- Create table for Token entity
CREATE TABLE tokens
(
    id         SERIAL PRIMARY KEY,
    token      VARCHAR(255) UNIQUE,
    token_type VARCHAR(255) NOT NULL,
    revoked    BOOLEAN      NOT NULL,
    expired    BOOLEAN      NOT NULL,
    user_id    UUID,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);
