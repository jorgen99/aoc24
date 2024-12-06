(ns jorgen.aoc24.dec06
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))

(def direction
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
  [(get direction char)
   (first (util/indicies-of-char char line))])


(defn dir-and-pos [line-no line]
  (->> (keys direction)
       (map #(leffe % line))
       (remove #(nil? (second %)))
       (map (fn [[dir col]] [dir [col line-no]]))
       (first)))

(let [lines (util/file->lines "dec06_input.txt")
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
                  (recur (rest remaining-lines) (inc line-no)))))
      ss (loop [steps []
                dir d
                current-pos p]
           (let [;_ (prn "-----------------")
                 ;_ (prn "dir" dir)
                 ;_ (prn "current-pos" current-pos)
                 ;M_ (prn "steps" steps)
                 char-at-next-step (util/value-in-grid grid (util/take-step current-pos dir))]
                 ;_ (prn "char-at-next-step" char-at-next-step)]
             (cond
               (nil? char-at-next-step) (conj steps current-pos)
               (= char-at-next-step "#") (recur steps (clockwise dir) current-pos)
               :else (recur (conj steps current-pos) dir (util/take-step  current-pos dir)))))]
 (count (distinct ss)))




(comment
  (time (part1 (util/file->lines "dec06_sample.txt"))))
;(time (part1 (util/file->lines "dec06_input.txt")))
;(time (part2 (util/file->lines "dec06_sample.txt")))
;(time (part2 (util/file->lines "dec06_input.txt"))))
