(ns jorgen.aoc24.dec07
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(let [lines (util/file->lines "dec07_sample.txt")
      line (first lines)
      [result nos]  (->> (str/split line #": ")
                         (map util/parse-ints))]
   [result nos])



(comment
  (time (part1 (util/file->lines "dec07_sample.txt"))))
;(time (part1 (util/file->lines "dec07_input.txt")))
;(time (part2 (util/file->lines "dec07_sample.txt")))
;(time (part2 (util/file->lines "dec07_input.txt"))))


