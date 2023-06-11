(ns aoc22.day1
  (:require
   [aoc22.core :refer [read-day-input string-to-int]]
   [clojure.string :refer [split-lines]]))

(def input (read-day-input "day1" :delimiter "\n\n"))

(defn merge-weights [elves] 
  (map (comp #(apply + %) #(map string-to-int %) split-lines) elves))

(defn part1 []
  (apply max (merge-weights input)))

(defn part2 []
  (apply + (take-last 3 (sort (merge-weights input)))))

