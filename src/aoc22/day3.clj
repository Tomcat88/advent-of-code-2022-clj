(ns aoc22.day3
  (:require
   [aoc22.core :refer [read-day-input]]
   [clojure.set :as set]))

(def input (read-day-input "day3"))

(defn base [c] (if (Character/isUpperCase c)
                 (+ (- (int c) (int \A)) 27)
                 (inc (- (int c) (int \a)))))

(defn find-shared-item [rucksack]
  (let [half (/ (count rucksack) 2)
        h1 (take half rucksack)
        h2 (drop half rucksack)]
    (first (set/intersection (set h1) (set h2)))))

(defn find-badge [rucksacks]
  (first (reduce set/intersection (map set rucksacks))))

(defn part1 []
  (apply + (map (comp base find-shared-item) input)))

(defn part2 []
  (apply + (map (comp base find-badge) (partition 3 input))))
