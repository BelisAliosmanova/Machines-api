-- Insert sample categories
INSERT INTO categories (id, created_at, updated_at, deleted_at, name)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Electronics'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Books'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Clothing'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Home & Garden'),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 'Sports & Outdoors');
