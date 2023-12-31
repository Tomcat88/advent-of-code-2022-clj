(ns aoc22.core
  (:require [clojure.string :as str]))

(defn read-file [day] (slurp (str "resources/" day ".txt") :encoding "UTF-8"))

(defn string-to-int [s] (. Integer parseInt s))

(defn read-day-input
  [day & {:keys [delimiter]}]
  (let [content (read-file day)]
    (if (nil? delimiter)
      (str/split-lines content)
      (str/split content (re-pattern delimiter)))))

(defn find-first
  "Finds the first element which respects the condition f"
  [f coll] (->> coll (filter f) first))
(defn to-index-vector [coll] (map-indexed vector coll))

