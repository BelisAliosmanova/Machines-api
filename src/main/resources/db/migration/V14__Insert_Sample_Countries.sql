-- Insert sample countries
INSERT INTO countries (id, created_at, updated_at, deleted_at, name)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'България');
