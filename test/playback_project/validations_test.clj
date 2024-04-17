(ns playback-project.validations-test
  (:require [clojure.test :refer :all]
            [playback-project.validations :as v]))

(defn to-vector [x] (into [] x))
; {:a "" :b "not empty"}
(deftest test-filter-strings
  (testing "Filter empty strings from map"
    (is (=  (to-vector {:a ""}) (v/filter-strings {:a "" :b "not empty"})))
    (is (= (to-vector {:a "" :b ""}) (v/filter-strings {:a "" :b ""})))
    (is (= () (v/filter-strings {:a "not empty" :b "not empty"})))))

(deftest test-filter-nil
  (testing "Filter nil values from map"
    (is (= (to-vector {:a nil}) (v/filter-nil {:a nil :b "not nil"})))
    (is (= (to-vector {:a nil :b nil}) (v/filter-nil {:a nil :b nil})))
    (is (= (to-vector {}) (v/filter-nil {:a "not nil" :b "not nil"})))))

(deftest test-validate-str
  (testing "Validate if value is not nil and not empty"
    (is (nil? (v/validate-str {:a "not empty" :b "not nil"})))
    (is (= "Invalid values: " (v/validate-str {:a "" :b "not nil"})))
    (is (= "Invalid values: (:a)" (v/validate-str {:a nil :b "not nil"})))))