-- Insert sample regions
INSERT INTO regions (id, created_at, updated_at, deleted_at, name, country_id)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'България',
        (SELECT id FROM countries WHERE name = 'България'));
