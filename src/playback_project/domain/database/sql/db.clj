
(ns playback-project.domain.database.sql.db
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter])
  (:gen-class))

(def db
  {:subname (str "//" (System/getenv "DB_HOST") ":" (System/getenv "DB_PORT") "/" (System/getenv "DB_NAME"))
   :host (System/getenv "DB_HOST")
   :port (System/getenv "DB_PORT")
   :dbname (System/getenv "DB_NAME")
   :subprotocol "postgres"
   :dbtype "postgres"
   :user (System/getenv "DB_USER")
   :password (System/getenv "DB_PASSWORD")})

(hugsql/def-db-fns
  "playback_project/domain/database/sql/positions-operations.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

(hugsql/def-sqlvec-fns
  "playback_project/domain/database/sql/positions-operations.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})