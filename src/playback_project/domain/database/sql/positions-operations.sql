-- A :returning_clause is used to return the values of the columns that were affected by the INSERT, UPDATE, or DELETE statement.
-- :name query-positions
-- :doc Query to get the positions of a user in a media
-- :result :many
SELECT user_id,
       title_id,
       media_id,
       position,
       finished
FROM playback.positions
WHERE user_id = :user_id_param
  AND media_id = :media_id_param
  AND title_id = :title_id_param
  AND finished = :finished_param
LIMIT :per_page OFFSET :page * :per_page;


-- A Calculate total pages
-- :name x-total-pages
-- :doc Calculate total of pages based on parameters
-- :result :one
SELECT (COUNT(*) / :per_page) AS total_pages
FROM playback.positions
WHERE user_id = :user_id_param
  AND media_id = :media_id_param
  AND title_id = :title_id_param
  AND finished = :finished_param;


-- :name insert-position
-- :doc Insert a new position
-- :result :one
INSERT INTO playback.positions (
    user_id,
    title_id, media_id, position, finished)
VALUES (:user_id::UUID, :title_id::UUID, :media_id::UUID, :position, :finished);