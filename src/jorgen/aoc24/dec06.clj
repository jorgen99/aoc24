(ns jorgen.aoc24.dec06
  (:require
    [jorgen.util :as util]))

(def nesw [:n :e :s :w])
(def eswn [:e :s :w :n])
(def dir-char [\^ \> \V \<])
(def clockwise (zipmap nesw eswn))
(def char->direction (zipmap dir-char nesw))


(defn direction-and-possible-start-idx [char line]
  [(get char->direction char)
   (first (util/indicies-of-char char line))])


(defn dir-and-pos [line-no line]
  (->> (keys char->direction)                               ; all possible
       (map #(direction-and-possible-start-idx % line))
       (remove #(nil? (second %)))
       (map (fn [[dir col]] [dir [col line-no]]))
       (first)))


(defn find-start-and-direction [lines]
  (loop [remaining-lines lines
         line-no 0]
    (let [[dir pos] (dir-and-pos line-no (first remaining-lines))]
      (if dir
        [dir pos]
        (recur (rest remaining-lines) (inc line-no))))))


(defn patrol [starting-pos direction grid]
  (loop [steps []
         dir direction
         current-pos starting-pos
         path-positions (sorted-set)]
    (let [next-pos (util/take-step current-pos dir)
          char-at-next-pos (util/value-in-grid grid next-pos)
          dir-current-pos [dir current-pos]
          updated-steps (conj steps dir-current-pos)]
      (cond
        (nil? char-at-next-pos) updated-steps
        (contains? path-positions dir-current-pos) []
        (= char-at-next-pos "#") (recur steps (clockwise dir) current-pos path-positions)
        :else (recur updated-steps dir next-pos (conj path-positions dir-current-pos))))))


(defn positions-in-original-path [start-pos direction grid]
  (->> (patrol start-pos direction grid)
       (map second)
       (distinct)))


(defn part1 [lines]
  (let [grid (util/parse-grid-of-chars lines)
        [direction start-pos] (find-start-and-direction lines)]
    (count (positions-in-original-path start-pos direction grid))))


(defn part2 [lines]
  (let [grid (util/parse-grid-of-chars lines)
        [direction start-pos] (find-start-and-direction lines)]
    ; Not the prettiest brute force solution, but it works
    ; - get distinct positions in the original path
    ; - take the original grid and set a # in one position
    ; - patrol, [] -> it's a loop
    (->> (positions-in-original-path start-pos direction grid)
         (map #(util/set-value-in-grid grid % "#"))
         (map #(patrol start-pos direction %))
         (filter empty?)
         (count))))


(comment
  (time (part1 (util/file->lines "dec06_sample.txt")))
  (time (part1 (util/file->lines "dec06_input.txt")))
  (time (part2 (util/file->lines "dec06_sample.txt")))
  (time (part2 (util/file->lines "dec06_input.txt"))))
