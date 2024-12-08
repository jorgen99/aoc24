(ns jorgen.aoc24.dec04
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


(defn four-steps-in-dir [pos dir]
  (loop [steps (repeat 3 dir)
         pos pos
         positions [pos]]
    (if (empty? steps)
      positions
      (let [next-pos (util/take-step pos (first steps))]
        (recur (rest steps)
               next-pos
               (conj positions next-pos))))))


(defn steps-in-dir [pos dirs initial-pos]
  (loop [steps dirs
         pos pos
         positions (if (nil? initial-pos) [] [initial-pos])]
    (if (empty? steps)
      positions
      (let [next-pos (util/take-step pos (first steps))]
        (recur (rest steps)
               next-pos
               (conj positions next-pos))))))


(defn count-xmas [grid current-pos]
  (->> util/all-directions
       (map #(four-steps-in-dir current-pos %))
       (remove #(some neg? (flatten %)))
       (map (fn [positions]
              (map #(util/value-in-grid grid %) positions)))
       (map str/join)
       (filter #(= "XMAS" %))
       (count)))


(defn cross? [word grid current-pos]
  (->> [[:nw :se :se] [:se :nw :nw] [:ne :sw :sw] [:sw :ne :ne]]
       (map #(steps-in-dir current-pos % nil))
       (remove #(some neg? (flatten %)))
       (map (fn [positions]
              (map #(util/value-in-grid grid %) positions)))
       (map str/join)
       (filter #(= word %))
       (count)
       (= 2)))


(defn solve [lines trigger-char count-fn]
  (let [grid (util/parse-grid-of-chars lines)
        coords (util/coordinates grid)]
    (loop [positions coords
           no-of-xmas 0]
      (if (empty? positions)
        no-of-xmas
        (let [current-pos (first positions)
              current-val (util/value-in-grid grid current-pos)]
          (if (= trigger-char current-val)
            (recur (rest positions) (+ no-of-xmas (count-fn grid current-pos)))
            (recur (rest positions) no-of-xmas)))))))


(defn part1 [lines]
  (solve lines "X" (fn [grid current-pos]
                     (count-xmas grid current-pos))))


(defn part2 [lines]
  (solve lines "A" (fn [grid current-pos]
                     (if (cross? "MAS" grid current-pos) 1 0))))

(comment
  (time (part1 (util/file->lines "dec04_sample.txt")))
  (time (part1 (util/file->lines "dec04_input.txt")))
  (time (part2 (util/file->lines "dec04_sample.txt")))
  (time (part2 (util/file->lines "dec04_input.txt"))))
