(ns jorgen.aoc24.dec03-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec03 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 161 (part1 (util/file->lines "dec03_sample.txt"))))
    (is (= 30243322 (part1 (util/file->lines "dec03_input.txt")))))


  (testing "It should "
    (is (= 48 (part2 (util/file->lines "dec03_sample2.txt"))))
    (is (= 84893551 (part2 (util/file->lines "dec03_input.txt"))))))
