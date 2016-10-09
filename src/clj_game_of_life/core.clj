(ns clj-game-of-life.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn print-row [row]
  (reduce str row))

(defn print-grid [grid]
  (map #(print "\n" (print-row %)) grid))

(defn row-out-of-bound? [idx grid]
  (cond
   (< idx 0) true
   (>= idx (count grid)) true
   :else false))

(defn col-out-of-bound? [idx grid]
  (cond
   (< idx 0) true
   (>= idx (count (first grid))) true
   :else false))

(defn live? [grid row col]
  (cond
   (row-out-of-bound? row grid) 0 ;; Return 0 since out of bound
   (col-out-of-bound? col grid) 0 ;; Return 0 since out of bound
   :else ((grid row) col))
  )

(defn count-live-neighbours [row col grid]
  ((frequencies [(live? grid (dec row) (dec col))
                 (live? grid (dec row) col)
                 (live? grid (dec row) (inc col))
                 (live? grid row (dec col))
                 (live? grid row (inc col))
                 (live? grid (inc row) (dec col))
                 (live? grid (inc row) col)
                 (live? grid (inc row) (inc col))]) 1))

(defn next-val [row col grid]
  (let [live? (= ((grid row) col) 1)
        live-neighbours (count-live-neighbours row col grid)]
    (cond
     live? (cond
              (> live-neighbours 3) 0 ;; live cell with more than
              ;; three live neighbours lives
              (>= live-neighbours 2) 1 ;; live cell with two or three
              ;; neighbour lives on to the next gen
              :else 0) ;; live cell fewer than two live neighbours dies
     :else (cond
            (= live-neighbours 3) 1 ;; dead cell with exactly three
            ;; live neighbours becomes live
            :else 0))))

(defn render-next-gen [grid]
  (into [] (map-indexed
    (fn [row tuple] (into [] (map-indexed (fn [col val] (next-val row col grid)) tuple))) grid)))

(defn tick [grid]
  (render-next-gen grid))
