-- Insert sample cities
INSERT INTO cities (id, created_at, updated_at, deleted_at, name, region_id)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Los Angeles',
        (SELECT id FROM regions WHERE name = 'California')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Toronto',
        (SELECT id FROM regions WHERE name = 'Ontario')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Guadalajara',
        (SELECT id FROM regions WHERE name = 'Jalisco')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'São Paulo',
        (SELECT id FROM regions WHERE name = 'São Paulo')),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Buenos Aires',
        (SELECT id FROM regions WHERE name = 'Buenos Aires'));
