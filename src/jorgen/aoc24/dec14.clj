(ns jorgen.aoc24.dec14
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(defn parse [lines]
  (->> lines
       (map #(re-matches #"p=(\d+),(\d+) v=(-?\d+),(-?\d+)" %))
       (map rest)
       (map #(mapv util/parse-int %))
       (map #(vector (take 2 %) (drop 2 %)))))


(let [lines (util/file->lines "dec14_sample.txt")
      positions (parse lines)]
  positions)


(defn part1 [lines]
  1)


(comment
  (time (part1 (util/file->lines "dec14_sample.txt"))))
;(time (part1 (util/file->lines "dec14_input.txt")))
;(time (part2 (util/file->lines "dec14_sample.txt")))
;(time (part2 (util/file->lines "dec14_input.txt"))))


