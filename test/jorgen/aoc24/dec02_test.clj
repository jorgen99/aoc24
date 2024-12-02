(ns jorgen.aoc24.dec02-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec02 :refer :all]))


(deftest testing-reports
  (testing "It should count the number of safe reports"
    (is (= 2 (part1 (util/file->lines "dec02_sample.txt"))))
    (is (= 486 (part1 (util/file->lines "dec02_input.txt")))))


  (testing "It should count the number of safe reports whith problem dampener"
    (is (= 4 (part2 (util/file->lines "dec02_sample.txt"))))
    (is (= 540 (part2 (util/file->lines "dec02_input.txt"))))))
