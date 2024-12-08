(ns jorgen.aoc24.dec07-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec07 :refer :all]))


(deftest testing-
  (testing "It should "
    (is (= 3749 (part1 (util/file->lines "dec07_sample.txt"))))
    (is (= 5030892084481 (part1 (util/file->lines "dec07_input.txt")))))


  (testing "It should "
    (is (= 11387 (part2 (util/file->lines "dec07_sample.txt"))))
    (is (= 91377448644679 (part2 (util/file->lines "dec07_input.txt"))))))
