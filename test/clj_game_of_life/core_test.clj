(ns clj-game-of-life.core-test
  (:require [clojure.test :refer :all]
            [clj-game-of-life.core :refer :all]))

;; Still lives

(def block [[0 0 0 0]
            [0 1 1 0]
            [0 1 1 0]
            [0 0 0 0]])

(def beehive [[0 0 0 0 0 0]
              [0 0 1 1 0 0]
              [0 1 0 0 1 0]
              [0 0 1 1 0 0]
              [0 0 0 0 0 0]])

(deftest still-live-test
  (testing "Still live patterns: Block"
    (is (= block (tick block)))
    (is (= block (tick (tick block))))
    (is (= block (tick (tick (tick block))))))
  
  (testing "Still live patterns: Beehive"
    (is (= beehive (tick beehive)))
    (is (= beehive (tick (tick beehive ))))
    (is (= beehive (tick (tick (tick beehive)))))))

;; Oscillators

(def blinker [[0 0 0 0 0]
              [0 0 0 0 0]
              [0 1 1 1 0]
              [0 0 0 0 0]
              [0 0 0 0 0]])

(def blinker-next [[0 0 0 0 0]
                   [0 0 1 0 0]
                   [0 0 1 0 0]
                   [0 0 1 0 0]
                   [0 0 0 0 0]])

(def toad [[0 0 0 0 0 0]
           [0 0 0 0 0 0]
           [0 0 1 1 1 0]
           [0 1 1 1 0 0]
           [0 0 0 0 0 0]
           [0 0 0 0 0 0]])

(def toad-next [[0 0 0 0 0 0]
                [0 0 0 1 0 0]
                [0 1 0 0 1 0]
                [0 1 0 0 1 0]
                [0 0 1 0 0 0]
                [0 0 0 0 0 0]])

(def beacon [[0 0 0 0 0 0]
             [0 1 1 0 0 0]
             [0 1 0 0 0 0]
             [0 0 0 0 1 0]
             [0 0 0 1 1 0]
             [0 0 0 0 0 0]])

(def beacon-next [[0 0 0 0 0 0]
                  [0 1 1 0 0 0]
                  [0 1 1 0 0 0]
                  [0 0 0 1 1 0]
                  [0 0 0 1 1 0]
                  [0 0 0 0 0 0]])

(deftest oscillator-test
  (testing "Blinker pattern"
    (is (= blinker-next (tick blinker)))
    (is (= blinker (tick (tick blinker))))
    (is (= blinker (tick blinker-next)))))
