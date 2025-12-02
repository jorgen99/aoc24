(ns jorgen.aoc24.dec11-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec11 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 55312 (part1 (util/file->lines "dec11_sample.txt"))))
    (is (= 233050 (part1 (util/file->lines "dec11_input.txt")))))


  (testing "It should "
    (is (= 65601038650482 (part2 (util/file->lines "dec11_sample.txt"))))
    (is (= 276661131175807 (part2 (util/file->lines "dec11_input.txt"))))))
