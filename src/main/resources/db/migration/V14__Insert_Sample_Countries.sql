-- Insert sample countries
INSERT INTO countries (id, created_at, updated_at, deleted_at, name)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'United States'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Canada'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Mexico'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Brazil'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Argentina');
