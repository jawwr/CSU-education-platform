create sequence if not exists image_sequence start 1;

create table if not exists images
(
    id   numeric primary key default nextval('image_sequence'::regclass),
    link varchar not null
);

create sequence if not exists category_sequence start 1;

create table if not exists categories
(
    id          numeric primary key default nextval('category_sequence'::regclass),
    name        varchar not null,
    description varchar not null,
    image_id    numeric
);