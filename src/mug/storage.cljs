(ns mug.storage
  (:require [mug.domain.eval :as e]
            [mug.symphony :refer [symphony]]))

(defn- ls-get [key fallback]
  (let [value (.getItem js/localStorage key)]
    (if (nil? value) fallback value)))

(defn- ls-set [key value]
  (.setItem js/localStorage key value))

(defn get-symph [] (ls-get "symphony" symphony))

(e/sub #(ls-set "symphony" %))
