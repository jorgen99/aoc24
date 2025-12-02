(ns jorgen.aoc24.dec13
  (:require
    [jorgen.util :as util]))

(def button-pattern #"Button [A|B]: X\+(\d+), Y\+(\d+)")
(def prize-pattern #"Prize: X=(\d+), Y=(\d+)")


(defn parse [pattern game lineNo]
  (let [[x y] (->> (nth game lineNo)
                   (re-matches pattern)
                   rest
                   (mapv util/parse-int))]
    {:x x :y y}))


(defn parse-game [prize-addition game]
  (let [a (parse button-pattern game 0)
        b (parse button-pattern game 1)
        prize (parse prize-pattern game 2)]
    {:ax (:x a)
     :ay (:y a)
     :bx (:x b)
     :by (:y b)
     :px (+ (:x prize) prize-addition)
     :py (+ (:y prize) prize-addition)}))


(defn solve [game]
  ;; A*ax + B*bx = px
  ;; A*ay + B*by = py
  ;;
  ;; A = (px - B*bx) / ax
  ;; A = (py - B*by) / ay
  ;;
  ;; (px - B*bx) / ax = (py - B*by) / ay
  ;; px*ay - B*bx*ay = py*ax - B*by*ax
  ;; px*ay - py*ax = B*bx*ay - B*by*ax
  ;;
  ;;     px*ay - py*ax
  ;; B = -------------
  ;;     bx*ay - by*ax
  (let [B (/
            (- (* (:px game) (:ay game))
               (* (:py game) (:ax game)))
            (- (* (:bx game) (:ay game))
               (* (:by game) (:ax game))))
        A (/
            (- (:px game) (* B (:bx game)))
            (:ax game))]
    (+ (* 3 A) B)))


(defn solver [parser-fn lines]
  (->> (util/parse-blocks lines)
       (map parser-fn)
       (map solve)
       (filter int?)
       (reduce +)))


(defn part1 [lines]
  (solver (partial parse-game 0) lines))


(defn part2 [lines]
  (solver (partial parse-game 10000000000000) lines))


(comment
  (time (part1 (util/file->lines "dec13_sample.txt")))
  (time (part1 (util/file->lines "dec13_input.txt")))
  (time (part2 (util/file->lines "dec13_sample.txt")))
  (time (part2 (util/file->lines "dec13_input.txt"))))
