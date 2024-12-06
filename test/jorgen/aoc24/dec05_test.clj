(ns jorgen.aoc24.dec05-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :as util]
    [jorgen.aoc24.dec05 :refer :all]))


(deftest testing-print-queue
  (testing "It should sum the middle value of the safe updates"
    (is (= 143 (part1 (util/file->lines "dec05_sample.txt"))))
    (is (= 4957 (part1 (util/file->lines "dec05_input.txt"))))))


  ;(testing "It should "
  ;  (is (= 1 (part2 (util/file->lines "dec05_sample.txt"))))
  ;  (is (= 1 (part2 (util/file->lines "dec05_input.txt"))))))

;{47 #{13 61 29 53},
; 97 #{75 13 61 29 47 53},
; 75 #{13 61 29 47 53},
; 61 #{13 29 53},
; 29 #{13},
; 53 #{13 29}}
;
;
;75,97,47,61,53 becomes 97,75,47,61,53.
;61,13,29 becomes 61,29,13.
;97,13,75,29,47 becomes 97,75,47,29,13.
