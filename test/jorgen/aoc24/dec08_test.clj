(ns jorgen.aoc24.dec08-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec08 :refer :all]))


(deftest testing-antinodes
  (testing "It should count antinodes"
    (is (= 14 (part1 (util/file->lines "dec08_sample.txt"))))
    (is (= 289 (part1 (util/file->lines "dec08_input.txt")))))


  (testing "It should count resonant antinodes"
    (is (= 34 (part2 (util/file->lines "dec08_sample.txt"))))
    (is (= 1030 (part2 (util/file->lines "dec08_input.txt"))))))
