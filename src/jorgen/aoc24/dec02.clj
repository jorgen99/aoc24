(ns jorgen.aoc24.dec02
  (:require
    [jorgen.util :as util]))


(defn safe? [line]
  (let [pairs (partition 2 1 line)
        diffs (map (fn [[a b]] (- a b)) pairs)]
    (or
      (every? #{1 2 3} diffs)
      (every? #{-1 -2 -3} diffs))))


(defn permutations-with-one-removed [line]
  (for [n (range 0 (count line))]
    (concat
      (take n line)
      (drop (inc n) line))))


(defn part1 [lines]
  (->> (util/grid-of-longs lines)
       (map safe?)
       (filter true?)
       count))


(defn part2 [lines]
  (->> (util/grid-of-longs lines)
       (map permutations-with-one-removed)
       (map #(map safe? %))
       (filter #(some true? %))
       count))


(comment
  (time (part1 (util/file->lines "dec02_sample.txt")))
  (time (part1 (util/file->lines "dec02_input.txt")))
  (time (part2 (util/file->lines "dec02_sample.txt")))
  (time (part2 (util/file->lines "dec02_input.txt"))))
