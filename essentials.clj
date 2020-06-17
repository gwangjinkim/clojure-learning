;; print commands


;; print to stdout with or witout newline and return nil
(println "A")
(print "A")

;; print into a string with or without newline and returning it (no tin stdout!)
(println-str "A")
(print-str "A")


;; concatenate strings
(str "a" "b" "c")
;; => "abc"

;; basic arithmetics + - * /
(+ 1 2 3)
;; => 6
(- 10 5 3)
;; => 2
(* 3 4 5)
;; => 60
(/ 10 2 2)
;; => 5/2



;; conditionals
true
false
;; everything except false and nil is true, even empty constructs

;; control flow IF
;; (if <conditional>
;;     <conditional-true-execute-this-form>
;;     <conditional-false-execute-this-form>)

(if true        (if false      (if true     (if false
    "A"             "A"            "A")         "A")
    "B")            "B")       ;; => "A"     ;; => nil
;; => "A"       ;; => "B"       

;; sqeeze more than one expression into each arm using
;; (do <expr1> <expr2> ...)







;; `progn` in common lisp is in clojure `do`
;; more than one expressions
(do "expr1"
    "expr2"
    "expr3")
