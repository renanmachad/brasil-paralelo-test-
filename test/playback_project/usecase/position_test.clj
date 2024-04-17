(ns playback-project.usecase.position-test
  (:require [clojure.test :refer :all]
            [playback-project.usecase.position :as position]
            [ring.mock.request :as mock])
  (:import (java.util UUID)))

(def position-id (str (UUID/randomUUID)))                   ;
(deftest test-persist-position
  (testing "Persist position"
    (let [position {:user_id  position-id
                    :title_id position-id
                    :media_id position-id
                    :position 1
                    :finished true}
          response (position/persist-position position)]
      (is (= 201 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"])))))) ; corrected this line

(deftest test-query-position
  (testing "Query positions"
    (let [response (position/query-position "0" "10" nil nil nil "true")]
      (is (= 200 (:status response)))
      (is (= "application/json" (get-in response [:headers "Content-Type"]))))))