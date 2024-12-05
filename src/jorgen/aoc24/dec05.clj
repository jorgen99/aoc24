(ns jorgen.aoc24.dec05
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))

(defn update-permutations [rules update]
  (for [n (range 0 (dec (count update)))]
    (conj []
          (get update n)
          (drop (inc n) update))))


(let [lines (util/file->lines "dec05_sample.txt")
      blocks (util/parse-blocks lines)
      pairs (->> (first blocks)
                 (map #(str/replace % #"\|" " "))
                 (map util/parse-ints))
      rules (into {} pairs)
      updates (->> (last blocks)
                   (map util/parse-ints))
      fupdate (first updates)
      perm (update-permutations rules fupdate)]

 (map #(contains? (set (second %)) (first %)) (first  perm)))
 ;(contains? (set (second (first  perm))) (first (first perm))))

  ;(split-at 1 fupdate))


(comment
  (time (part1 (util/file->lines "dec05_sample.txt"))))
;(time (part1 (util/file->lines "dec05_input.txt")))
;(time (part2 (util/file->lines "dec05_sample.txt")))
;(time (part2 (util/file->lines "dec05_input.txt"))))


