(ns jorgen.aoc24.dec03
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(def mul-pattern #"mul\((\d{1,3}),(\d{1,3})\)")
(def dont-pattern #"don\'t\(\)")
(def do-pattern #"do\(\)")


(defn sum-line [line]
  (let [matches (re-seq mul-pattern line)]
    (->> matches
         (map #(drop 1 %))
         (map #(mapv util/parse-int %))
         (map #(apply * %))
         (reduce +))))


(defn part1 [lines]
  (let [line (first lines)
        matches (re-seq mul-pattern line)]
    (->> matches
         (map #(drop 1 %))
         (map #(mapv util/parse-int %))
         (map #(apply * %))
         (reduce +))))


(defn part2 [lines]
  (let [line (str/join lines)]
    (loop [result 0
           parts (str/split line dont-pattern 2)]
      (if (empty? parts)
        result
        (let [new-result (+ result (sum-line (first parts)))]
          (if (empty? (second parts))
            new-result
            (let [do-match (str/split (second parts) do-pattern 2)
                  dont-match (if (= 1 (count do-match))
                               []
                               (str/split (second do-match) dont-pattern 2))]
              (recur new-result dont-match))))))))


(comment
  (time (part1 (util/file->lines "dec03_sample.txt")))
  (time (part1 (util/file->lines "dec03_input.txt")))
  (time (part2 (util/file->lines "dec03_sample2.txt")))
  (time (part2 (util/file->lines "dec03_input.txt"))))


