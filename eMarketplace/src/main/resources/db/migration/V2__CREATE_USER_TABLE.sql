CREATE TABLE IF NOT EXISTS public.users
(
    id character varying NOT NULL,
    username character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    birthday character varying NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(id, username, email)
);

ALTER TABLE listings
ADD COLUMN author_username character varying NOT NULL;