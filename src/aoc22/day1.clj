(ns aoc22.day1
  (:require
   [aoc22.core :refer [read-day-input string-to-int]]
   [clojure.string :refer [split-lines]]))

(def input (read-day-input "day1" :delimiter "\n\n"))

(defn part1 []
  (apply max (map (comp #(apply + %) #(map string-to-int %) split-lines) input)))

(defn part2 []
  (apply + (take-last 3 (sort (map (comp #(apply + %) #(map string-to-int %) split-lines) input)))))

