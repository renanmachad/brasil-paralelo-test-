(ns playback-project.usecase.position
  (:require [clojure.tools.logging :as log]
            [clojure.data.json :as json]
            [playback-project.domain.database.sql.db :refer :all])
  (:gen-class)
  (:import (java.util UUID)))

(defn persist-position [position]
  (log/info "Persisting position" position)
  ; persist in database
  (try
    (insert-position db position)
    {:status 201 :headers {"Content-Type" "application/json"}}
    (catch Exception e
      (log/error e "Error while inserting position")
      {:status 400 :headers {"Content-Type" "application/json"}})))


(defn query-position [page per_page user_id title_id media_id finished]
  (log/info "Querying positions" page per_page user_id title_id media_id finished)
  (let [finished (Boolean/parseBoolean finished)
        result (query-positions db {:page (or (Integer/parseInt page) 0)
                                    :per_page (or (Integer/parseInt per_page) 0)
                                    :user_id user_id
                                    :title_id title_id
                                    :media_id media_id
                                    :finished finished})
        result (map #(update % :user_id str) result)
        result (map #(update % :title_id str) result)
        result (map #(update % :media_id str) result)
        total-pages (query-total-pages db {:per_page (or (Integer/parseInt per_page) 0)
                                           :user_id user_id
                                           :title_id title_id
                                           :finished finished
                                           :media_id media_id})]
    (log/info "Result" result)
    (log/info "Total pages" total-pages)
    (log/debug "Total pages" (get total-pages :total_pages))
    {:status 200 :headers {
                           "Content-Type" "application/json"
                           "x-page" (str page)
                           "x-per-page" (str per_page)
                           "x-total" (str (count result))
                           "x-total-pages" (str (get total-pages :total_pages))
                           }
     :body (json/write-str result)}))