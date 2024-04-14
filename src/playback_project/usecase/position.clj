(ns playback-project.usecase.position
  (:require [clojure.tools.logging :as log]
            [playback-project.domain.database.sql.db :refer :all])
  (:gen-class)
  )

(defn persist-position [position]
  (log/info "Persisting position" position)
  ; persist in database
  (try
    (insert-position db position)
    {:status 201 :headers {"Content-Type" "application/json"}}
    (catch Exception e
      (log/error e "Error while inserting position")
      {:status 400 :headers {"Content-Type" "application/json"}})))