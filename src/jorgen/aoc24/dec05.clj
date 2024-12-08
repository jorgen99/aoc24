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
  (let [middle-idx (int (/ (count xs) 2))]
    (xs middle-idx)))


(defn safe-calc [rules updates]
  (let [safe? (partial safe-with-rules? rules)]
    (->> updates
         (filter #(every? true? (safe? %)))
         (map middle-value))))


(defn part1 [lines]
  (let [blocks (util/parse-blocks lines)
        rules (rule-hist (first blocks))
        updates (mapv util/parse-ints (last blocks))]
    (reduce + (safe-calc rules updates))))


(defn rule-sort [rules updates]
  (let [comparator (fn [x y]
                     (cond ((rules x #{}) y) -1
                           ((rules y #{}) x) 1
                           :else 0))]
    (vec (sort comparator updates))))


(defn part2 [lines]
   (let [blocks (util/parse-blocks lines)
         rules (rule-hist (first blocks))
         safe? (partial safe-with-rules? rules)
         unsafe (->> (last blocks)
                     (map util/parse-ints)
                     (remove #(every? true? (safe? %))))]
     (->> unsafe
           (map (partial rule-sort rules))
           (safe-calc rules)
           (reduce +))))


(comment
  (time (part1 (util/file->lines "dec05_sample.txt")))
  (time (part1 (util/file->lines "dec05_input.txt")))
  (time (part2 (util/file->lines "dec05_sample.txt")))
  (time (part2 (util/file->lines "dec05_input.txt"))))
