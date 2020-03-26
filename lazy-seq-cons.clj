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
;; => (1 8 27 64)

(take 10 (do-to-all square (range 0 1000)))
;; => (0 1 4 9 16 25 36 49 64 81)

;; ***
(take 10 (do-to-all square (range 0 10000000000)))
;; => Execution error (StackOverflowError) at user/do-to-all (REPL:3).
;; => null


;; ***
;; how to fix it via lazy eval? (we can't use in this case recur)
(defn do-to-all
  [f numbers]
  (lazy-seq 
    (if (empty? numbers)
      nil
      (cons (f (first numbers)) (do-to-all f (rest numbers))) )))

;; ***
;; Now, because this return is a lazy sequence, it no longer attempts to recursively compute
;; all the elements to return. The lazy-seq macro takes a body of code that returns
;; a sequence (or nil) and returns an object that’s “seqable”—that is, it behaves like a
;; sequence. But it invokes the body only once and on demand (lazily) and returns cached results thereafter. 

(take 10 (do-to-all square (range 0 100000000000000000)))
;; => (0 1 4 9 16 25 36 49 64 81)

;;still it will suffer for stackoverflow, how to fix that , see recur in "clojure for the brave and true repo"
(take 1000000 (do-to-all square (range 0 100000000000000000)))
























