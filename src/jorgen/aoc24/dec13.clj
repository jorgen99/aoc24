(ns jorgen.aoc24.dec13
  (:require
    [clojure.string :as str]
    [jorgen.util :as util]))

(def button-pattern #"Button [A|B]: X\+(\d+), Y\+(\d+)")
(def prize-pattern #"Prize: X=(\d+), Y=(\d+)")


(defn parse [pattern game lineNo]
  (let [[x y] (->> (nth game lineNo)
                   (re-matches pattern)
                   rest
                   (mapv util/parse-int))]
    {:x x :y y}))

(defn parse-game [game]
  (let [a (parse button-pattern game 0)
        b (parse button-pattern game 1)
        prize (parse prize-pattern game 2)]
    {:a a :b b :prize prize}))


(let [lines (util/file->lines "dec13_sample.txt")
      game-blocks (util/parse-blocks lines)
      games (->> game-blocks
                 (map parse-game))
      first-game (first games)
      a-cost 3
      b-cost 1]
  ((juxt quot rem) (get-in first-game [:prize :x]) (get-in first-game [:b :x])))

((juxt quot rem) 8400 22)
(util/lcm 94 22)
(quot (util/lcm 94 22) 94)
(* (quot (util/lcm 94 22) 94) 22)



(comment
  (time (part1 (util/file->lines "dec13_sample.txt"))))
;(time (part1 (util/file->lines "dec13_input.txt")))
;(time (part2 (util/file->lines "dec13_sample.txt")))
;(time (part2 (util/file->lines "dec13_input.txt"))))


