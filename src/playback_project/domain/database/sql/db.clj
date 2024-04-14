
(ns playback-project.domain.database.sql.db
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.next-jdbc :as next-adapter])
  (:gen-class))
(def db
  {:subname "//db:5432/postgres"
   :host "localhost"
   :port "5432"
   :dbname "postgres"
   :subprotocol "postgres"
   :dbtype "postgres"
   :user "postgres"
   :password "postgres"})

(hugsql/def-db-fns
  "playback_project/domain/database/sql/positions-operations.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

(hugsql/def-sqlvec-fns
  "playback_project/domain/database/sql/positions-operations.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})