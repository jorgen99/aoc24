(ns jorgen.util-test
  (:require
    [clojure.test :refer :all]
    [jorgen.util :refer :all]))


(deftest testing-helpers
  (testing "It should take one step"
    (let [current-pos [2 3]]
      (is (= [2 2] (take-step current-pos :n)))
      (is (= [2 4] (take-step current-pos :s)))
      (is (= [3 3] (take-step current-pos :e)))
      (is (= [1 3] (take-step current-pos :w)))
      (is (= [3 2] (take-step current-pos :ne)))
      (is (= [3 4] (take-step current-pos :se)))
      (is (= [1 4] (take-step current-pos :sw)))
      (is (= [1 2] (take-step current-pos :nw)))))

  (testing "It should swap values in a vector"
    (let [col [0 1 2 3 4 5 6 7]]
      (is (= [7 1 2 3 4 5 6 0] (swap 7 0 col)))
      (is (= [0 1 2 4 3 5 6 7] (swap 4 3 col)))
      (is (= [0 1 2 4 3 5 6 7] (swap 3 4 col))))))
