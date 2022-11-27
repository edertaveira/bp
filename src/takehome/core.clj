(ns takehome.core
  (:require [java-time :as time]))

(defn patriota-access [object]
  (or (= (:type object) :podcast) (= (:type object) :interview) (= (:type object) :debate)))

(defn premium-access [object]
  (or (patriota-access object) (= (:type object) :course)))

(defn mecenas-access [object]
  (or (premium-access object) (= (:type object) :patron)))

(defn valid-subscription? [object purchase]
  (time/before? (:subscription-start purchase)
                (:released-at object)
                (:subscription-end purchase)))

(defn can-access? [object purchase]
  (if (= (:type object) :series) true (case (:type purchase)
                                         :patriota (and (patriota-access object) (valid-subscription? object purchase))
                                         :premium (premium-access object)
                                         :mecenas (and (mecenas-access object) (valid-subscription? object purchase)))))
