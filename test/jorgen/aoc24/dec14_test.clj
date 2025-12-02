(ns jorgen.aoc24.dec14-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec14 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 1 (part1 (util/file->lines "dec14_sample.txt"))))
    (is (= 1 (part1 (util/file->lines "dec14_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec14_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec14_input.txt"))))))
