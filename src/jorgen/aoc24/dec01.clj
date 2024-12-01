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

(defn part1 [lines]
  (->> (location-ids lines)
       (total-distance)))

(comment
  (time (part1 (util/file->lines "dec01_sample.txt")))
  (time (part1 (util/file->lines "dec01_input.txt"))))
;(time (part2 (util/file->lines "dec01_sample.txt")))
;(time (part2 (util/file->lines "dec01_input.txt"))))


