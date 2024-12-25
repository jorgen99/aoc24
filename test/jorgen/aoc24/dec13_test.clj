(ns jorgen.aoc24.dec13-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec13 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 1 (part1 (util/file->lines "dec13_sample.txt"))))
    (is (= 1 (part1 (util/file->lines "dec13_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec13_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec13_input.txt"))))))
