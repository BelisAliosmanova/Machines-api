-- Insert sample files
INSERT INTO files (id, created_at, updated_at, deleted_at, name, type, path, size)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL,
        'accb2310-8be3-4a1c-8c81-29de136209cb.png',
        '.png',
        'https://firebasestorage.googleapis.com/v0/b/localweb-428009.appspot.com/o/accb2310-8be3-4a1c-8c81-29de136209cb.png?alt=media',
        928),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL,
        '947f048e-3238-4c2c-ac92-0aa35e8824b3.png',
        '.png',
        'https://firebasestorage.googleapis.com/v0/b/localweb-428009.appspot.com/o/947f048e-3238-4c2c-ac92-0aa35e8824b3.png?alt=media',
        1195);
