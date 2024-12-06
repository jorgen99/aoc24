(ns jorgen.aoc24.dec06
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))

(def char->direction
  {\^ :n
   \> :e
   \V :s
   \< :w})

(def clockwise
  {:n :e
   :e :s
   :s :w
   :w :n})

(defn leffe [char line]
  [(get char->direction char)
   (first (util/indicies-of-char char line))])


(defn dir-and-pos [line-no line]
  (->> (keys char->direction)
       (map #(leffe % line))
       (remove #(nil? (second %)))
       (map (fn [[dir col]] [dir [col line-no]]))
       (first)))


(defn walk [starting-pos direction grid]
  (loop [steps []
         dir direction
         current-pos starting-pos
         visited #{}]
    (let [_ (prn "-----------------")
          _ (prn "dir" dir)
          _ (prn "current-pos" current-pos)
          _ (prn "steps" steps)
          char-at-next-step (util/value-in-grid grid (util/take-step current-pos dir))
          _ (prn "char-at-next-step" char-at-next-step)
          loop? (contains? visited current-pos)]
      (cond
        (nil? char-at-next-step) (conj steps [dir current-pos])
        (= char-at-next-step "#") (recur steps (clockwise dir) current-pos)
        :else (recur (conj steps [dir current-pos]) dir (util/take-step  current-pos dir))))))


(let [lines (util/file->lines "dec06_sample.txt")
      grid (util/parse-grid-of-chars lines)
      [dir pos] (->> lines
                     (map #(dir-and-pos 6 %))
                     (remove nil?)
                     (first))
      [d p] (loop [remaining-lines lines
                   line-no 0]
              (let [[dir pos] (dir-and-pos line-no (first remaining-lines))]
                (if dir
                  [dir pos]
                  (recur (rest remaining-lines) (inc line-no)))))]
      ;ss (walk p d grid)]
 (->> (walk p d grid)
      (map second)
      distinct
      count))





(comment
  (time (part1 (util/file->lines "dec06_sample.txt"))))
;(time (part1 (util/file->lines "dec06_input.txt")))
;(time (part2 (util/file->lines "dec06_sample.txt")))
;(time (part2 (util/file->lines "dec06_input.txt"))))
