(ns jorgen.aoc24.dec13-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec13 :refer :all]))


(deftest testing-
  (testing "It should calculate tokens"
    (is (= 480 (part1 (util/file->lines "dec13_sample.txt"))))
    (is (= 39290 (part1 (util/file->lines "dec13_input.txt")))))


  (testing "It should calculate tokens with large x and y"
    (is (= 875318608908 (part2 (util/file->lines "dec13_sample.txt"))))
    (is (= 73458657399094 (part2 (util/file->lines "dec13_input.txt"))))))
