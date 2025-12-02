(ns jorgen.aoc24.dec09
  (:require
    [jorgen.util :as util]))


(defn defrag [line]
  (let [free-indicies (util/indicies-of-char "." line)
        free-space (count free-indicies)
        idx-when-done (dec (- (count line) free-space))]
    (loop [col line
           idx (dec (count line))
           idx-of-free free-indicies]
       (if (= idx idx-when-done)
          col
          (let [c (nth col idx)
                first-free (first idx-of-free)]
            (if (= c ".")
              (recur col (dec idx) idx-of-free)
              (recur (util/swap idx first-free col) (dec idx) (rest idx-of-free))))))))


(defn ->blocks [disc-map]
  (persistent!
    (loop [disk (transient [])
           idx 0
           digits disc-map
           id 0]
      (if (empty? digits)
        disk
        (let [digit (util/char->int (first digits))]
          (if (even? idx)
            (recur (reduce conj! disk (repeat digit id)) (inc idx) (rest digits) (inc id))
            (recur (reduce conj! disk (repeat digit ".")) (inc idx) (rest digits) id)))))))


(defn checksum [defraged-blocks]
  (reduce (fn [acc [idx d]]
            (if (= d ".")
              (reduced acc)
              (+ acc (* idx d))))
          0
          (map-indexed vector defraged-blocks)))


(let [lines (util/file->lines "dec09_sample.txt")]
  (->> lines
       (map ->blocks)
       (map defrag)
       (map checksum)
       (reduce +)))

(defn part1 [lines]
  (->> lines
       (map ->blocks)
       (map defrag)
       (map checksum)
       (reduce +)))



(comment
  (time (part1 (util/file->lines "dec09_sample.txt")))
  (time (part1 (util/file->lines "dec09_input.txt"))))
;(time (part2 (util/file->lines "dec09_sample.txt")))
;(time (part2 (util/file->lines "dec09_input.txt"))))


