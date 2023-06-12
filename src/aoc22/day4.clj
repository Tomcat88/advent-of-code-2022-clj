(ns aoc22.day4
  (:require
   [aoc22.core :refer [read-day-input]]
   [clojure.string :as str]
   [clojure.edn :as edn]))

(defn normalize-range [str] (map #(map edn/read-string (str/split % #"-")) (str/split str #",")))
(def input (map normalize-range (read-day-input "day4")))

(defn range-contains? [[[l1 r1] [l2 r2]]]
  (cond
    (and (>= l1 l2) (<= r1 r2)) true
    (and (>= l2 l1) (<= r2 r1)) true
    :else false))

(defn range-overlaps? [[[l1 r1] [l2 r2]]]
  (cond
    (range-contains? [[l1 r1] [l2 r2]]) true
    (and (>= l1 l2) (<= l1 r2)) true
    (and (>= l2 l1) (<= l2 r1)) true
    :else false))

(defn part1 []
  (->> input
       (map range-contains?)
       (filter true?)
       count))

(defn part2 []
  (->> input
       (map range-overlaps?)
       (filter true?)
       count))
