(ns clj-game-of-life.core-test
  (:require [clojure.test :refer :all]
            [clj-game-of-life.core :refer :all]))

;; Test Still lives

(def block [[0 0 0 0]
            [0 1 1 0]
            [0 1 1 0]
            [0 0 0 0]])

(def beehive [[0 0 0 0 0 0]
              [0 0 1 1 0 0]
              [0 1 0 0 1 0]
              [0 0 1 1 0 0]
              [0 0 0 0 0 0]])

(deftest still-life-test
  (testing "Still life patterns: Block"
    (is (= block (tick block)))
    (is (= block (tick (tick block))))
    (is (= block (tick (tick (tick block))))))
  
  (testing "Still life patterns: Beehive"
    (is (= beehive (tick beehive)))
    (is (= beehive (tick (tick beehive ))))
    (is (= beehive (tick (tick (tick beehive)))))))

;; Test Oscillators

;; Initial pattern for blinker
(def blinker [[0 0 0 0 0]
              [0 0 0 0 0]
              [0 1 1 1 0]
              [0 0 0 0 0]
              [0 0 0 0 0]])

;; Next step for blinker
(def blinker-next [[0 0 0 0 0]
                   [0 0 1 0 0]
                   [0 0 1 0 0]
                   [0 0 1 0 0]
                   [0 0 0 0 0]])

;; Initial pattern for Beacon
(def beacon [[0 0 0 0 0 0]
             [0 1 1 0 0 0]
             [0 1 0 0 0 0]
             [0 0 0 0 1 0]
             [0 0 0 1 1 0]
             [0 0 0 0 0 0]])


;; Next step for Beacon
(def beacon-next [[0 0 0 0 0 0]
                  [0 1 1 0 0 0]
                  [0 1 1 0 0 0]
                  [0 0 0 1 1 0]
                  [0 0 0 1 1 0]
                  [0 0 0 0 0 0]])

;; Initial pattern for Toad
(def toad [[0 0 0 0 0 0]
           [0 0 0 0 0 0]
           [0 0 1 1 1 0]
           [0 1 1 1 0 0]
           [0 0 0 0 0 0]
           [0 0 0 0 0 0]])

;; Next step (period 1) step for Toad
(def toad-next [[0 0 0 0 0 0]
                [0 0 0 1 0 0]
                [0 1 0 0 1 0]
                [0 1 0 0 1 0]
                [0 0 1 0 0 0]
                [0 0 0 0 0 0]])

(deftest oscillator-test
  (testing "Blinker pattern"
    (is (= blinker-next (tick blinker)))
    (is (= blinker (tick (tick blinker))))
    (is (= blinker (tick blinker-next))))

  (testing "Beacon pattern"
    (is (= beacon-next (tick beacon)))
    (is (= beacon (tick (tick beacon))))
    (is (= beacon (tick beacon-next))))

  (testing "Toad pattern"
    (is (= toad-next (tick toad)))
    (is (= toad (tick (tick toad))))
    (is (= toad (tick toad-next)))))

;; Spaceships

;; Init pattern for glider
(def glider [[1 0 1 0 0 0 0 0 0 0]
             [0 1 1 0 0 0 0 0 0 0]
             [0 1 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]
             [0 0 0 0 0 0 0 0 0 0]])

;; Second step for glider pattern
(def glider-28th [[0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 1 0 1]
                  [0 0 0 0 0 0 0 0 1 1]
                  [0 0 0 0 0 0 0 0 1 0]])

(deftest spaceship-test
  (testing "Testing Glider pattern for 28 ticks"
    (is (= glider-28th (tick glider 28))))) ;; Run game of life for
;; period 28 i.e 28 ticks
