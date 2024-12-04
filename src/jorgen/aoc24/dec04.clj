(ns jorgen.aoc24.dec04
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))

;(defn get4 [grid pos dir]
;  (reduce (fn [acc [x y]])))



(let [lines (util/file->lines "dec04_sample.txt")
      grid (util/parse-grid-of-chars lines)
      _ (for [y (range 0 (count grid))
              x (range 0 (count (first grid)))]
          (util/value-in-grid grid [x y]))
      pos [4 4]
      _ [[4 4] [4]]
      positions (get4 grid pos :e)]
  four)

(comment
  (time (part1 (util/file->lines "dec04_sample.txt"))))
;(time (part1 (util/file->lines "dec04_input.txt")))
;(time (part2 (util/file->lines "dec04_sample.txt")))
;(time (part2 (util/file->lines "dec04_input.txt"))))

