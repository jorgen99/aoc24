(ns jorgen.aoc24.dec01
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(defn location-ids [lines]
  (->> lines
       (map (fn [s] (str/split s #"\s+")))
       (map (fn [[a b]] [(util/parse-int a) (util/parse-int b)]))
       util/transpose))


(defn total-distance [locations]
  (->> (map sort locations)
       (partition 2)
       (map (fn [m] (map - (first m) (last m))))
       (first)
       (map abs)
       (reduce +)))


(defn occurances [xs]
  (reduce (fn [acc x]
            (update acc x (fnil inc 0)))
          {}
          xs))


(defn proximity-score [ids hist]
  (reduce (fn [acc x]
            (+ acc (* x (get hist x 0))))
          0
          ids))


(defn part1 [lines]
  (->> (location-ids lines)
       (total-distance)))


(defn part2 [lines]
  (let [[ids1 ids2] (location-ids lines)
        hist (occurances ids2)]
    (proximity-score ids1 hist)))


(comment
  (time (part1 (util/file->lines "dec01_sample.txt")))
  (time (part1 (util/file->lines "dec01_input.txt")))
  (time (part2 (util/file->lines "dec01_sample.txt")))
  (time (part2 (util/file->lines "dec01_input.txt"))))
