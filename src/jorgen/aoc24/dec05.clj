(ns jorgen.aoc24.dec05
  (:require
    [clojure.set :as set]
    [clojure.string :as str]
    [jorgen.util :as util]))


(defn hist [pairs]
  (reduce (fn [m [k v]]
            (update m k (fnil conj #{}) v))
          {}
          pairs))


(defn rule-hist [lines]
  (->> lines
       (map #(str/replace % #"\|" " "))
       (map util/parse-ints)
       hist))


(defn update-permutations [update]
  (for [n (range 0 (dec (count update)))]
    (conj []
          (set (take n update))
          (get update n)
          (set (drop (inc n) update)))))


(defn safe-with-rules? [rules update]
  (->> (update-permutations update)
       (map (fn [[before no after]]
              (let [rule-vals (get rules no)]
                (and
                  (boolean (some after rule-vals))
                  (not (boolean (some before rule-vals)))))))))


(defn middle-value [xs]
  (nth xs (/ (count xs) 2)))


(defn part1 [lines]
  (let [blocks (util/parse-blocks lines)
        rules (rule-hist (first blocks))
        safe? (partial safe-with-rules? rules)]
    (->> (last blocks)
         (map util/parse-ints)
         (filter #(every? true? (safe? %)))
         (map middle-value)
         (reduce +))))


(defn move-left [idx col]
  (concat
    (take (dec idx) col)
    (conj [] (nth col idx) (nth col (dec idx)))
    (drop (inc idx) col)))

(let [lines (util/file->lines "dec05_sample.txt")
      blocks (util/parse-blocks lines)
      rules (rule-hist (first blocks))
      safe? (partial safe-with-rules? rules)
      unsafe (->> (last blocks)
                  (map util/parse-ints)
                  (remove #(every? true? (safe? %))))
      apa [97 13 75 29 47]
      biff (->> (last blocks)
                (map util/parse-ints)
                (map #(safe? %))
                (remove #(every? true? %)))]
      ;(move-left 2 apa)])
  (move-left 2 apa))


(nth [1 2 3 4] 2)
;(map middle-value)))
;(reduce +)))


(comment
  (time (part1 (util/file->lines "dec05_sample.txt")))
  (time (part1 (util/file->lines "dec05_input.txt"))))
;(time (part2 (util/file->lines "dec05_sample.txt")))
;(time (part2 (util/file->lines "dec05_input.txt"))))


