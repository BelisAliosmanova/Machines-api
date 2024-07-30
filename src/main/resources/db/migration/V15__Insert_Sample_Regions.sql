-- Insert sample regions
INSERT INTO regions (id, created_at, updated_at, deleted_at, name, country_id)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'California',
        (SELECT id FROM countries WHERE name = 'United States')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Ontario',
        (SELECT id FROM countries WHERE name = 'Canada')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Jalisco',
        (SELECT id FROM countries WHERE name = 'Mexico')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'São Paulo',
        (SELECT id FROM countries WHERE name = 'Brazil')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Buenos Aires',
        (SELECT id FROM countries WHERE name = 'Argentina'));
