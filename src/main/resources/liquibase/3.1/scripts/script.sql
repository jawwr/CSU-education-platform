create TABLE IF NOT EXISTS theories
(
    id numeric primary key,
    text varchar not null,
    page_number numeric not null,
    image_id numeric not null,
    theme_id numeric not null
);

CREATE UNIQUE INDEX IF NOT EXISTS theories__theme_id_page_number_uidx ON theories(theme_id, page_number);
