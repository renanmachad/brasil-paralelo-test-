CREATE SCHEMA IF NOT EXISTS playback;
CREATE TABLE IF NOT EXISTS playback.positions
(
    user_id  uuid not null,
    title_id uuid not null,
    media_id uuid not null,
    position int  not null,
    finished boolean default false,
    PRIMARY KEY (user_id, title_id, media_id)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO playback.positions(user_id, title_id, media_id, position, finished)
VALUES (uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), 360, false);


INSERT INTO playback.positions(user_id, title_id, media_id, position, finished)
VALUES (uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), 1000, false);

INSERT INTO playback.positions(user_id, title_id, media_id, position, finished)
VALUES (uuid_generate_v4(), uuid_generate_v4(), uuid_generate_v4(), 1200, false);