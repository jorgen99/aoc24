(ns jorgen.aoc24.dec09-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec09 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 1928 (part1 (util/file->lines "dec09_sample.txt"))))
    (is (= 6279058075753 (part1 (util/file->lines "dec09_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec09_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec09_input.txt"))))))
