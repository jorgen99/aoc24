(ns jorgen.aoc24.dec06-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec06 :refer :all]))


(deftest testing-patrol-locations
  (testing "It should count the number of positions visited"
    (is (= 41 (part1 (util/file->lines "dec06_sample.txt"))))
    (is (= 4711 (part1 (util/file->lines "dec06_input.txt")))))


  (testing "It should count the number of possible loops"
    (is (= 6 (part2 (util/file->lines "dec06_sample.txt"))))
    (is (= 1562 (part2 (util/file->lines "dec06_input.txt"))))))
