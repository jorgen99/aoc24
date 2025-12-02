(ns jorgen.aoc24.dec10
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


;; fun surrounding(x: Int, y: Int, matrix: List<List<GridLocation>>): List<GridLocation> {
;;     return orthogonalDirections.values
;;         .asSequence()
;;         .map { (dx, dy) -> x + dx to y + dy }
;;         .filterNot { (x, _) -> x < 0 }
;;         .filterNot { (x, _) -> x >= matrix[0].size }
;;         .filterNot { (_, y) -> y < 0 }
;;         .filterNot { (_, y) -> y >= matrix.size }
;;         .map { (x, y) -> matrix[y][x] }
;;         .toList()
;; }

(defn path [pos grid]
  (let [height (util/value-in-grid grid pos)
        path-height (inc height)]
    (->> (util/orthogonal-neighbors pos grid)
         (map #(do {:pos % :height (util/value-in-grid grid %)}))
         (filter #(= path-height (:height %))))))


(let [lines (util/file->lines "dec10_sample1.txt")
      grid (util/parse-grid-of-ints lines)
      _ (prn "grid" grid)
      pos' [0 0]])
  ;(->> grid
  ;     (cat (fn [v] (map str v)))])

(defn part1 [lines]
  1)


(comment)
 ;(time (part1 (util/file->lines "dec10_sample.txt"))))
 ;(time (part1 (util/file->lines "dec10_input.txt")))
 ;(time (part2 (util/file->lines "dec10_sample.txt"))))
 ;(time (part2 (util/file->lines "dec10_input.txt"))))


