CREATE TABLE books (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(200) NOT NULL,
                       author VARCHAR(150),
                       type VARCHAR(20) NOT NULL,  -- "BOOK" veya "MANGA"
                       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
