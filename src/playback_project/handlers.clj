(ns playback-project.handlers
  (:require
    [clojure.tools.logging :as log]
    [clojure.data.json :as json]
    [playback-project.usecase.position :refer :all ]
    [playback-project.validations :refer :all])
  (:import (java.io InputStreamReader)))

(defn save-position [req]
  (let [body-stream (:body req)
        reader (InputStreamReader. body-stream)
        position (json/read reader :key-fn keyword)
        required-keys [:user_id :title_id :media_id :position]
        missing-keys (some (fn [k] (when (not (contains? position k)) k)) required-keys)
        missing-str (validate-str position)
        valid-position-value (fn [x]
                               (if (and (integer? (get x :position)) (not= (get x :position) nil))
                                 true
                                 false))
        ]
    (log/info "Position req" position)
    (log/info "Missing attrs" missing-keys)
    (log/info "Not found strings" missing-str)
    (log/info "Valid position value" (valid-position-value position))
    (if (or missing-keys missing-str  (not (valid-position-value position)))
      {:status 422 :headers {"Content-Type" "application/json"}}
      (persist-position position))))

(defn get-positions [req]
  (log/info "QUERY-POSITIONS PARAMS" :params req)
  (let [page (get-in req [:params :page])
        per_page (get-in req [:params :per_page])
        user_id (get-in req [:params :user_id])
        title_id (get-in req [:params :title_id])
        media_id (get-in req [:params :media_id])
        finished (get-in req [:params :finished])
        ]
    (query-position page per_page user_id title_id media_id finished)))

