(ns jorgen.aoc24.dec07
  (:require
    [clojure.string :as str]
    [clojure.math.combinatorics :as comb]
    [jorgen.util :as util]))


(defn apply-ops [ops numbers r]
  (reduce
    (fn [acc ops-seq]
      (let [result (reduce (fn [acc [op num]]
                             (op acc num))
                           (first numbers)
                           (map vector ops-seq (rest numbers)))]
        (cond
          (= result r) (reduced [result])
          :else (conj acc result))))
    []
    ops))


(defn solve [lines ops]
  (let [calibrations (->> lines
                          (map #(str/split % #": "))
                          (map #(mapv util/parse-longs %))
                          (map (fn [[[sum] & nos]]
                                 (cons sum nos))))]
    (->> calibrations
         (map (fn [[r numbers]]
                (let [ops (apply comb/cartesian-product (repeat (dec (count numbers)) ops))]
                  (apply-ops ops numbers r))))
         (filter #(= 1 (count %)))
         (flatten)
         (reduce +))))


(defn part1 [lines]
  (solve lines [+ *]))


(defn || [a b]
  (parse-long (str/join [(str a) (str b)])))


(defn part2 [lines]
  (solve lines [+ * ||]))


(comment
  (time (part1 (util/file->lines "dec07_sample.txt")))
  (time (part1 (util/file->lines "dec07_input.txt")))
  (time (part2 (util/file->lines "dec07_sample.txt")))
  (time (part2 (util/file->lines "dec07_input.txt"))))
