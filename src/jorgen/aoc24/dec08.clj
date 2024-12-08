(ns jorgen.aoc24.dec08
  (:require
    [jorgen.util :as util]))


(defn antenna-positions
  " grid -> antenna positions

  {'0' [[8 1] [5 2] [7 3] [4 4]], 'A' [[6 5] [8 8] [9 9]]}
  "
  [grid]
  (reduce (fn [acc pos]
            (let [v (util/value-in-grid grid pos)]
              (if (not= "." v)
                (update acc v (fnil conj []) pos)
                acc)))
          {}
          (util/coordinates grid)))


(defn antenna-buddies
  "antenna position -> other antennas positions of the same type

  [[6 5] [8 8] [9 9]] ->

    {[6 5] ([8 8] [9 9]),
     [8 8] ([6 5] [9 9]),
     [9 9] ([6 5] [8 8])}
  "
  [antennas]
  (reduce (fn [acc idx]
            (let [next-idx (inc idx)]
              (assoc acc (nth antennas idx)
                         (concat
                           (take idx antennas)
                           (drop next-idx antennas)))))
          {}
          (range 0 (count antennas))))

(defn antenna-pairs
  "pair antennas with their buddies

  {[6 5] ([8 8] [9 9])} ->

  (([[6 5] [8 8]] [[6 5] [9 9]])
   ([[8 8] [6 5]] [[8 8] [9 9]])
   ([[9 9] [6 5]] [[9 9] [8 8]]))
  "
  [amap]
  (map (fn [[antenna buddies]]
         (util/zip
           (repeat (count buddies) antenna)
           buddies))
       amap))

#_(defn opposite [[c1 c2]]
    (->> (util/zip c1 c2)
         (map #(apply - %))
         (map abs)
         (util/zip c2)
         (map #(apply + %))))

(defn diff [[x1 y1] [x2 y2]]
  [(- x2 x1) (- y2 y1)])


(defn opposite [[[x1 y1] [x2 y2]]]
  (let [[dx dy] (diff [x1 y1] [x2 y2])]
    [(+ x2 dx) (+ y2 dy)]))


(defn line [[width length] [[x1 y1] [x2 y2]]]
  (let [d (diff [x1 y1] [x2 y2])]
    (loop [acc []
           pos [x1 y1]]
      (let [[nx ny] (mapv + pos d)]
        (if (or (neg? nx) (> nx (dec width))
                (neg? ny) (> ny (dec length)))

          acc
          (recur (conj acc [nx ny]) [nx ny]))))))


(defn part1 [lines]
  (let [grid (util/parse-grid-of-chars lines)
        [width length] [(count (first lines)) (count lines)]
        antennas (antenna-positions grid)
        pairs (for [key (keys antennas)]
                (->> (get antennas key)
                     antenna-buddies
                     antenna-pairs))]
    (->> pairs
         (map (fn [a]
                (map (fn [b]
                       (map opposite b)) a)))
         flatten
         (partition 2)
         (remove #(some neg? %))
         (remove (fn [[x y]]
                   (or (> x (dec width))
                       (> y (dec length)))))
         distinct
         count)))


(defn part2 [lines]
  (let [grid (util/parse-grid-of-chars lines)
        [width length] [(count (first lines)) (count lines)]
        antennas (antenna-positions grid)
        pairs (for [key (keys antennas)]
                (->> (get antennas key)
                     antenna-buddies
                     antenna-pairs))
        straight (partial line [width length])]

    (->> pairs
         (map (fn [a]
                (map (fn [b]
                       (map straight b)) a)))
         flatten
         (partition 2)
         distinct
         count)))


(comment
  (time (part1 (util/file->lines "dec08_sample.txt")))
  (time (part1 (util/file->lines "dec08_input.txt")))
  (time (part2 (util/file->lines "dec08_sample.txt")))
  (time (part2 (util/file->lines "dec08_input.txt"))))


