CREATE TABLE IF NOT EXISTS playback.positions
(
    user_id  uuid not null,
    title_id uuid not null,
    media_id uuid not null,
    position int  not null,
    finished boolean default false,
    primary key (user_id, title_id, media_id)
);