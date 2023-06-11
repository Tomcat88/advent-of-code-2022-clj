(ns aoc22.day2
  (:require
   [aoc22.core :refer [read-day-input]]))

(def base-opp (int \A))
(def base-me (int \X))

(def input  (map (fn [row] [(inc (- (int (first row)) base-opp)) (inc (- (int (last row)) base-me))]) (read-day-input "day2")))

; 1, A, X -- Rock
; 2, B, Y -- Paper
; 3, C, Z -- Scissor

(defn round1 [[opp me]]
  (condp = me
    1 (condp = opp 1 3 2 0 3 6)
    2 (condp = opp 1 6 2 3 3 0)
    3 (condp = opp 1 0 2 6 3 3)))

(defn round2 [[opp result]]
  (let [me (condp = result
             1 (condp = opp 1 3 2 1 3 2)
             2 opp
             3 (condp = opp 1 2 2 3 3 1))]
    (+ me (round1 [opp me]))))

(defn part1 []
  (reduce + (map #(+ (round1 %) (last %)) input)))

(defn part2 []
  (reduce + (map round2 input)))
