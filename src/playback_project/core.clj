(ns playback-project.core
  (:require
            [org.httpkit.server :as  server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [playback-project.handlers :refer :all]
            [clojure.tools.logging :as log])
  (:gen-class)
  )

(defroutes app-routes
           (GET "/" [] "Hello World")
           (POST "/positions" [req]  save-position)
           (GET "/positions" [req] get-positions )
           (GET "/json" [] {:status 200 :headers {"Content-Type" "application/json"} :body (json/write-str {:message "Hello World"})})
           (route/not-found "Not Found"))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults)  {:port port})
    (log/info "Running service on port " port)
    (println (str "Running service on port " port))))