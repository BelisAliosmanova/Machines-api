-- Insert sample files
INSERT INTO files (id, created_at, updated_at, deleted_at, name, type, path, size)
VALUES (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL,
        '041ac8f3-4e17-409c-a3b1-1b44452cc869.jpg',
        '.jpg',
        'https://firebasestorage.googleapis.com/v0/b/localweb-428009.appspot.com/o/041ac8f3-4e17-409c-a3b1-1b44452cc869.jpg?alt=media',
        928),
       (uuid_generate_v4(), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL,
        '1f92af23-4c73-4a50-bcf1-bebb5951b6dd.jpg',
        '.jpg',
        'https://firebasestorage.googleapis.com/v0/b/localweb-428009.appspot.com/o/1f92af23-4c73-4a50-bcf1-bebb5951b6dd.jpg?alt=media',
        1195);
