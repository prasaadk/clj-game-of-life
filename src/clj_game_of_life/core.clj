(ns clj-game-of-life.core
  (:gen-class))

(defn print-row [row]
  (reduce str row))

(defn print-grid [grid]
  (map #(print "\n" (print-row %)) grid))

(defn row-out-of-bound?
  "Takes an index as 'idx' and the grid. Checks if the row is out of bounds for the grid dimensions."
  [idx grid]
  (cond
   (< idx 0) true
   (>= idx (count grid)) true
   :else false))

(defn col-out-of-bound?
  "Takes an index as 'idx' and the grid. Checks if the column is out of bounds for the grid dimensions."
  [idx grid]
  (cond
   (< idx 0) true
   (>= idx (count (first grid))) true
   :else false))

(defn live?
  "Takes row, col and the grid. Checks if the cell represented by row, col is live. Returns '1' if live, '0' if dead or out of bounds."
  [grid row col]
  (cond
   (row-out-of-bound? row grid) 0 ;; Return 0 since out of bound
   (col-out-of-bound? col grid) 0 ;; Return 0 since out of bound
   :else ((grid row) col))
  )

(defn count-live-neighbours
  "Takes row, col and the grid. Counts numbers of live cells around the cell represented by row, col."
  [row col grid]
  ((frequencies [(live? grid (dec row) (dec col))
                 (live? grid (dec row) col)
                 (live? grid (dec row) (inc col))
                 (live? grid row (dec col))
                 (live? grid row (inc col))
                 (live? grid (inc row) (dec col))
                 (live? grid (inc row) col)
                 (live? grid (inc row) (inc col))]) 1))

(defn live-or-die?
  "Takes row, col and grid. Calculates the new cell value for cell represented by row, col. Decides whether cell should live or die. '1' for live and '0' for die."
  [row col grid]
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

(defn render-next-gen
  "Generates the subsequent state of the grid"
  [grid]
  (into [] (map-indexed
    (fn [row tuple] (into [] (map-indexed (fn [col val] (live-or-die? row col grid)) tuple))) grid)))

(defn tick
  "Takes a 2 dimensional grid represented as vectors of vectors (e.g [[0 0] [1 0]]). Returns next generation of the grid on applying game of life population rules"
  ([grid]
     (render-next-gen grid))
  ([grid n]
     (loop [gen grid
            cnt n]
                                        ; If count reaches 0 then exit the loop and return gen
       (if (= cnt 0)
         gen
                                        ; Otherwise execute 1 tick on gen, decrease count and 
                                        ; use recur to feed the new values back into the loop
         (recur (tick gen) (dec cnt))))))
