create sequence IF NOT EXISTS theme_sequence START 1;


create TABLE IF NOT EXISTS themes
(
    id numeric primary key default nextval('theme_sequence'::regclass),
    name varchar not null,
    description varchar not null,
    category_id numeric not null,
    image_id numeric not null
);
