(ns jorgen.aoc24.dec11
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(defn apply-rules [no]
  (cond
    (= 0 no) [1]
    (even? (count (str no))) (->> (util/halves (str no))
                                  (mapv util/parse-int))
    :else [(* no 2024)]))


(def blinking
  (memoize
    (fn [stone blinks]
      (if (zero? blinks)
        1
        (->> (apply-rules stone)
             (map #(blinking % (dec blinks)))
             (reduce +))))))


(defn solve [blinks lines]
  (->>
    (util/parse-ints (first lines))
    (map #(blinking % blinks))
    (reduce +)))


(defn part1 [lines]
  (let [line (first lines)
        numbers (mapv util/parse-int (str/split line #" "))]
    (->> numbers
         (iterate #(mapcat apply-rules %))
         (take 26)
         last
         count)))


(defn part2 [lines]
  (solve 75 lines))


(comment
  (time (part1 (util/file->lines "dec11_sample.txt")))
  (time (part1 (util/file->lines "dec11_input.txt")))
  (time (part2 (util/file->lines "dec11_sample.txt")))
  (time (part2 (util/file->lines "dec11_input.txt"))))
