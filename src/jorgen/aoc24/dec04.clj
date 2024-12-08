(ns jorgen.aoc24.dec04
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))


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


(defn words-from-grid [positions word grid]
  (let [_ (prn "__________________")
        _ (prn "positions" positions)
        _ (prn "word" word)
        apa (->> positions
                 (remove #(some neg? (flatten %)))
                 (map (fn [positions]
                        (map #(util/value-in-grid grid %) positions)))
                 (map str/join)
                 (filter #(= word %)))]
    apa))


(defn count-words [word grid current-pos]
  (->> util/all-directions
       (map #(steps-in-dir current-pos (repeat (dec (count word)) %) current-pos))
       #(words-from-grid % word grid)))
       ;(count)))


(defn part1 [lines]
  (let [grid (util/parse-grid-of-chars lines)]
    (loop [positions (util/coordinates grid)
           no-of-xmas 0]
      (if (empty? positions)
        no-of-xmas
        (let [current-pos (first positions)
              current-val (util/value-in-grid grid current-pos)]
          (if (= "X" current-val)
            (recur (rest positions)
                   (+ no-of-xmas (count-words "XMAS" grid current-pos)))
            (recur (rest positions) no-of-xmas)))))))


(defn mas-cross? [word grid current-pos]
  (->> [[:nw :se :se] [:se :nw :nw] [:ne :sw :sw] [:sw :ne :ne]]
       (map #(steps-in-dir current-pos % nil))
       (remove #(some neg? (flatten %)))
       (map (fn [positions]
              (map #(util/value-in-grid grid %) positions)))
       (map str/join)
       (filter #(= word %))
       (count)
       (= 2)))

(let [lines (util/file->lines "dec04_sample.txt")
      grid (util/parse-grid-of-chars lines)
      coords (for [y (range 0 (count lines))
                   x (range 0 (count (first grid)))]
               [x y])
      current-pos [4 1]
      word "XMAS"]
  #_(loop [positions coords
           no-of-xmas 0]
      (if (empty? positions)
        no-of-xmas
        (let [current-pos (first positions)
              current-val (util/value-in-grid grid current-pos)]
          (if (= "A" current-val)
            (recur (rest positions)
                   (+ no-of-xmas (mas-cross? "MAS" grid current-pos)))
            (recur (rest positions) no-of-xmas)))))

  #_(mas-cross? "MAS" grid [2 1])


  (->> util/all-directions
       (map #(steps-in-dir current-pos (repeat (dec (count word)) %) current-pos))
       (map (fn [positions] (words-from-grid positions word grid))))
       ;(count)))

  (words-from-grid (steps-in-dir [4 1] (repeat (dec (count "XMAS")) :w) [4 1])
                   "XMAS" grid))

(comment
  (time (part1 (util/file->lines "dec04_sample.txt")))
  (time (part1 (util/file->lines "dec04_input.txt"))))
;(time (part2 (util/file->lines "dec04_sample.txt")))
;(time (part2 (util/file->lines "dec04_input.txt"))))


