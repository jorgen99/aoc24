(ns jorgen.aoc24.dec06-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec06 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 1 (part1 (util/file->lines "dec06_sample.txt"))))
    (is (= 1 (part1 (util/file->lines "dec06_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec06_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec06_input.txt"))))))
