(ns jorgen.aoc24.dec03
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(def mul-pattern #"mul\((\d{1,3}),(\d{1,3})\)")
(def dont-pattern #"don\'t\(\)")
(def do-pattern #"do\(\)")
(def dont-do-pattern #"don\'t\(\).*?do\(\)")

(defn sum-line [line]
  (let [matches (re-seq mul-pattern line)]
    (->> matches
         (map #(drop 1 %))
         (map (fn [[a b]]
                [(util/parse-int a) (util/parse-int b)]))
         (map #(apply * %))
         (reduce +))))


(defn remove-dont [line]
  (str/replace line (re-find dont-do-pattern line) ""))

(let [lines (util/file->lines "dec03_input.txt")
      line (str/join lines)
      parts (str/split line dont-pattern)
      _ (spit "resources/apa.txt" (first parts))]
   (count parts))


(let [lines (util/file->lines "dec03_input.txt")
      line (str/join lines)]

  line)


(let [lines (util/file->lines "dec03_input.txt")]
  (->> (map remove-dont lines)
       (map sum-line)
       (reduce +)))

(comment
  (time (part1 (util/file->lines "dec03_sample.txt"))))
;(time (part1 (util/file->lines "dec03_input.txt")))
;(time (part2 (util/file->lines "dec03_sample.txt")))
;(time (part2 (util/file->lines "dec03_input.txt"))))


