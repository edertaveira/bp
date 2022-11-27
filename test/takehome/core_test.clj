(ns takehome.core-test
  (:require [clojure.test :refer :all]
            [java-time :as time]
            [takehome.core :as sub]))

;; Patriota
(deftest test-patriota-access-series
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :patriota
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    true {:type               :patriota
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-access-podcast
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :patriota
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    false {:type               :patriota
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-access-debate 
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :debate :name "O que realmente é a felicidade?", :released-at (time/local-date-time "2020-03-29T20:02:34.353")}
                             purchase))
    true  {:type               :patriota
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :patriota
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-access-interview 
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579")}
                             purchase))
    true  {:type               :patriota
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :patriota
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-access-course
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :course :name "Fundações do Pensamento Político Brasileiro", :released-at (time/local-date-time "2020-02-20T21:31:27.592")}
                             purchase))
    false  {:type               :patriota
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :patriota
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-patriota-access-patron
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656")}
                             purchase))
    false  {:type               :patriota
            :subscription-start (time/local-date-time "2019-08-24T11:46:22.811")
            :subscription-end   (time/local-date-time "2020-08-24T11:46:22.811")}
    false {:type               :patriota
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

;; Premium
(deftest test-premium-access-series
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :premium
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    true {:type               :premium
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium-access-podcast
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :premium
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    true {:type               :premium
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium-access-debate
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :debate :name "O que realmente é a felicidade?", :released-at (time/local-date-time "2020-03-29T20:02:34.353")}
                             purchase))
    true  {:type               :premium
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    true {:type               :premium
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium-access-interview
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579")}
                             purchase))
    true  {:type               :premium
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    true {:type               :premium
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium-access-course
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :course :name "Fundações do Pensamento Político Brasileiro", :released-at (time/local-date-time "2020-02-20T21:31:27.592")}
                             purchase))
    true  {:type               :premium
            :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
            :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    true {:type               :premium
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-premium-access-patron
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656")}
                             purchase))
    false  {:type               :premium
           :subscription-start (time/local-date-time "2019-08-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-08-24T11:46:22.811")}
    false {:type               :premium
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

;; Mecenas
(deftest test-mecenas-access-series
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :series :name "1964: O Brasil entre Armas e Livros", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :mecenas
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    true {:type               :mecenas
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-mecenas-access-podcast
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :podcast :name "CORINGA - Podcast Cultura Paralela #1", :released-at (time/local-date-time "2019-07-24T20:02:34.691")}
                             purchase))
    true  {:type               :mecenas
           :subscription-start (time/local-date-time "2019-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-01-24T11:46:22.811")}
    false {:type               :mecenas
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-mecenas-access-debate
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :debate :name "O que realmente é a felicidade?", :released-at (time/local-date-time "2020-03-29T20:02:34.353")}
                             purchase))
    true  {:type               :mecenas
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :mecenas
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-mecenas-access-interview
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :interview :name "Congresso Brasil Paralelo - Rafael Nogueira", :released-at (time/local-date-time "2019-11-16T21:40:51.579")}
                             purchase))
    true  {:type               :mecenas
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :mecenas
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-mecenas-access-course
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :course :name "Fundações do Pensamento Político Brasileiro", :released-at (time/local-date-time "2020-02-20T21:31:27.592")}
                             purchase))
    true  {:type               :mecenas
           :subscription-start (time/local-date-time "2019-05-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2020-05-24T11:46:22.811")}
    false {:type               :mecenas
          :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
          :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

(deftest test-mecenas-access-patron
  (are [result purchase] (= result
                            (sub/can-access?
                             {:type :patron :name "Relatório Mecenas", :released-at (time/local-date-time "2020-08-10T20:00:00.656")}
                             purchase))
    true  {:type               :mecenas
            :subscription-start (time/local-date-time "2019-08-24T11:46:22.811")
            :subscription-end   (time/local-date-time "2020-08-24T11:46:22.811")}
    false {:type               :mecenas
           :subscription-start (time/local-date-time "2017-01-24T11:46:22.811")
           :subscription-end   (time/local-date-time "2019-01-24T11:46:22.811")}))

