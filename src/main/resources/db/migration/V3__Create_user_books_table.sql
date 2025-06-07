CREATE TABLE user_books (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                            user_id UUID NOT NULL REFERENCES users(id),
                            book_id UUID NOT NULL REFERENCES books(id),
                            added_at TIMESTAMP NOT NULL DEFAULT NOW(),
                            UNIQUE(user_id, book_id)
);
