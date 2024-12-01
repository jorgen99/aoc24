(ns jorgen.aoc24.dec01-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec01 :refer :all]))


(deftest testing-
  (testing "It should calculate the total distance"
    (is (= 11 (part1 (util/file->lines "dec01_sample.txt"))))
    (is (= 2285373 (part1 (util/file->lines "dec01_input.txt")))))


  (testing "It should calculate the proximity score"
    (is (= 31 (part2 (util/file->lines "dec01_sample.txt"))))
    (is (= 21142653 (part2 (util/file->lines "dec01_input.txt"))))))
