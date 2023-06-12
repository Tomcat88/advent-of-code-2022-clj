(ns aoc22.day5
  (:require
   [aoc22.core :refer [read-day-input]]
   [clojure.string :as str]
   [clojure.edn :as edn]))

(def input (read-day-input "day5" :delimiter "\n\n"))
(def stack-range (range 9))

(defn get-column
  "Given a list of list, get the col columns"
  [rows col]
  (map #(nth % col) rows))

(defn parse-stack-column
  "Given a column remove last element and unwanted chars"
  [column]
  (filter #(and (not= \space %) (not= \. %)) (map #(nth % 1) (drop-last 1 column))))

(defn parse-columns
  "partition every row to get all cranes elements, and then pivot it by taking the columns"
  []
  (let [rows (map #(partition 3 4 " " %) (str/split-lines (first input)))]
    (map #(parse-stack-column (get-column rows %)) stack-range)))

(def stacks
  "Creates a map where each key is the crane number and the value is the list of boxes from top to bottom"
  (let [columns (parse-columns)]
    (reduce #(assoc %1 %2 (nth columns %2)) {} stack-range)))

(defn parse-move [move]
  (let [m (re-matcher #"move (\d+) from (\d) to (\d)" move)
        [_ times from to] (re-find m)]
    [(edn/read-string times)
     (dec (edn/read-string from))
     (dec (edn/read-string to))]))

(def moves (map parse-move (str/split-lines (last input))))

(defn move
  "Move one crate from one stack to another, if times is supplied repeates the same move multiple times."
  ([stacks from to]
   (move stacks 1 from to :multiple))
  ([stacks times from to]
   (reduce (fn [map _] (move map from to)) stacks (range times)))
  ([stacks times from to & {:keys [multiple]}]
   (let [stack-from (get stacks from)
         crates (take times stack-from)
         stack-to (get stacks to)]
     (assoc stacks
            from (drop times stack-from)
            to (concat crates stack-to)))))

(defn part1 []
  (let [moved-stacks
        (reduce
         (fn [map [times from to]] (move map times from to))
         stacks
         moves)]
    (map #(first (get moved-stacks %)) stack-range)))

(defn part2 []
  (let [moved-stacks
        (reduce
         (fn [map [times from to]] (move map times from to :multiple))
         stacks
         moves)]
    (map #(first (get moved-stacks %)) stack-range)))

