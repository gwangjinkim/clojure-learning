




;; map, reduce
;; take a sequence/seq
;; the seq library
;; seq functions

;; a data structur "implements" a sequence abstraction

;; lists, vectors, sets, maps



(defn exclamat [x] (str x "!"))

(map exclamate ["A" "B"])                       ;; => ("A!" "B!")
(map exclamate ["A" "B"])                       ;; => ("A!" "B!")
(map exclamate #{"A" "B"})                      ;; => ("A!" "B!")
(map #(exclamate (second %)) {:A "A" :B :B"})   ;; => ("A!" "B!")

;; implementation of linked list in javascript
;; with cons first rest

;; seq does an indirection
(seq '(1 2 3))    ;; => (1 2 3)
(seq [1 2 3])     ;; => (1 2 3)
(seq #{1 2 3})    ;; => (1 2 3)
(seq {:a "a" :b "b"}) ;; => ([:a "a"] [:b "b"])

;; convert seq back to map
(into {} (seq {:a 1 :b 2 :c 3}))  ;; => {:a 1 :b 2 :c 3}

;; seq functions apply seq on their args

;; reduce filter distinct group-by and dozens more

;; concentrat on what we can do with a data structure and ignore its implementations
;; program to abstractions - and become implementation-independent!



