CREATE TABLE IF NOT EXISTS public.listings
(
    id character varying NOT NULL,
    name character varying NOT NULL,
    price double precision NOT NULL,
    description text,
    submission_time character varying NOT NULL,
    photo_url character varying NOT NULL,
    PRIMARY KEY (id)
);