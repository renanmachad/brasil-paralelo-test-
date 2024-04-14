(ns playback-project.validations,
  (:require [clojure.tools.logging :as log]))

(defn filter-strings
  "Filter  empty strings from map"
  [m]
  (filter (fn [[k v]] (if (= v "") true false)) m)
  )

(defn filter-nil
  "Filter nil values from map. Return a new map with only non nil values."
  [m]
  (filter (fn [[k v]] (nil? v)) m))


(defn validate-str
  "Validate if value is not nil and not empty (\"\")"
  [value]
  (let [strings (filter-strings value)
        nil-values (filter-nil value)
        ]
    (log/debug "Strings found" strings)
    (log/debug "Non nil strings" nil-values)
    (if (and (empty? strings) (empty? nil-values))
      nil
      (str "Invalid values: " (keys nil-values)))))