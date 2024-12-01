(ns jorgen.aoc24.dec01-test
  (:require
    [clojure.test :refer :all]
    [jorgen.aoc24.util :as util]
    [jorgen.aoc24.dec01 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 1 (part1 (util/file->lines "dec01_sample.txt"))))
    (is (= 1 (part1 (util/file->lines "dec01_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part (util/file->lines "dec01_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec01_input.txt"))))))
