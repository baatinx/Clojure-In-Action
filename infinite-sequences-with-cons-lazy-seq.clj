(defn square 
  [x]
  (* x x))

(defn square-all
  [numbers]
  (if (empty? numbers)
        nil
        (cons (square (first numbers)) (square-all (rest numbers)) )))    

(square-all [1 2 3 4])
;; => (1 4 9 16)

(square-all (range 0 100000000))
;; => Execution error (StackOverflowError) at user/square (REPL:3).
;; => null
;; - see recur & tail call optimization in "clojure for the brave and true" repo



(defn cube 
  [x]
  ( * x x x))

(defn cube-all
  [numbers]
  (if (empty? numbers)
    nil
    (cons (cube (first numbers)) (cube-all (rest numbers)))))

(cube-all [1 2 3 4])
;; => (1 8 27 64)

(defn do-to-all
  [f numbers]
  (if (empty? numbers)
    nil
    (cons (f (first numbers)) (do-to-all f (rest numbers))) ))

(do-to-all square [1 2 3 4])
;; => (1 4 9 16)
(do-to-all cube [1 2 3 4])
