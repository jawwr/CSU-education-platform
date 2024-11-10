create sequence if not exists mini_games_seq start 1;
create table if not exists mini_games
(
    id          numeric primary key default nextval('mini_games_seq'::regclass),
    name        varchar not null,
    question    varchar not null,
    category_id numeric not null,
    image_id    numeric not null
);

create sequence if not exists mini_games_choices_seq start 1;
create table if not exists mini_games_choices
(
    id           numeric primary key default nextval('mini_games_choices_seq'::regclass),
    mini_game_id numeric not null,
    is_correct   boolean not null,
    title        varchar not null,
    image_id     numeric not null,
    constraint choice_mini_game
        foreign key (mini_game_id)
            references mini_games (id)
);
