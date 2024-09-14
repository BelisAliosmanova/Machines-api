UPDATE offers
SET unique_short_id = floor(random() * 9000 + 1000)::bigint
WHERE unique_short_id IS NULL;