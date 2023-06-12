(ns aoc22.day6
  (:require [aoc22.core :refer [to-index-vector read-day-input find-first]]))

(def input (first (read-day-input "day6")))

(def parts-signal (partition 4 1 input))
(def parts-message (partition 14 1 input))

(defn find-first-element-unique [coll]
  (find-first
   #(= (count (set (second %))) (count (second %)))
   (to-index-vector coll)))

(defn part1 []
  (let [[n signal] (find-first-element-unique parts-signal)]
    (+ n (count signal))))

(defn part2 []
  (let [[n message] (find-first-element-unique parts-message)]
    (+ n (count message))))
