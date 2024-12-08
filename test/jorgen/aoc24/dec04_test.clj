(ns jorgen.aoc24.dec04-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec04 :refer :all]))


#_(deftest testing-count-xmas
    (testing "It should count XMAS in the grid"
      (is (= 18 (part1 (util/file->lines "dec04_sample.txt"))))
      (is (= 2560 (part1 (util/file->lines "dec04_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec04_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec04_input.txt"))))))
