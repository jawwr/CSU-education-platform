create sequence if not exists super_games_seq start 1;
create table if not exists super_games
(
    id             numeric primary key default nextval('super_games_seq'::regclass),
    title          varchar not null,
    object_to_find varchar not null,
    category_id    numeric not null
);
