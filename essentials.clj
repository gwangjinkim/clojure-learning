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



;; important predicates
(nil? nil)             ;; => true
(nil? <anything-else>) ;; => false

;; equality
(= 1 1)                ;; => true
(= nil nil)            ;; => true
(= 1 2)                ;; => true
;; clojure equality is simple, only `=` needed



;; truthiness in clojure

;; Clojure:      `false` and `nil`                 => "false"
;;                everything else including `true` => "true"
;; Racket/Scheme: `#f`                             => "false"
;;                everything else including `#t`   => "true"
;;                (there is no nil)
;; Common Lisp:   nil, '(), (), 'nil               => "false"
;;                everything else including `t`    => "true"
;;                (there is no false, only nil)


;; shortcircuitry of `OR` and `AND`:

;; OR:  first truthy value returned or last falsy value
;; AND: first falsy value returned or last truthy value

;; falsy: false or nil

(or false nil :a :b)   ;; => :a ;; :b not evaluated!
(or (= 0 1) nil)                ;; => nil
(or nil (= 0 1) (= "yes" "no")) ;; => false

(and (= 1 0) (= 2 2))             ;; => false (= 1 0) ;; (= 2 2) not evaluated!
(and (= 1 1) nil (= 1 0) (= 2 2)) ;; => nil ;; the rest not evaluated!
(and (= 1 1) (= 2 2))             ;; => true (= 2 2)


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




;; assignments 

;; global
(def x 3)
(+ x 1)
;; => 4

;; local           ;; let can do multi assignments
(let [x 3]         (let [x 3 y 4]
    (+ x 1))         (+ x y))
;;=> 4             ;; => 7

;; let can even `destructure` sequences
(let [[a b c] '(1 2 3)
      [v w] [4 5]]
    [a b c v w])
;; => [1 2 3 4 5]



;; maps
{}
(hash-map)

(def dct-1 {:first-name "Charlie" :last-name "McFishwich"})
(def dct-2 {"string-key" +})
(def dct-3 (hash-map :a 1 :b 2))

;; access keys `get`
(get dct-1 :first-name) ;;=> "charlie
(get dct-2 "string-key")
;; => #object[clojure.core$_PLUS_ 0x799159 "clojure.core$_PLUS_@799159"]
(get dct-3 :a)   ;; => 1

;; if key is a symbol, you can use it as getter function
(:first-name dct-1)     ;; => "charlie"
;; ("string-key" dct-2) ;; => error! only key symbols usable as accessor
(:a dct-3)              ;; => 1

;; `get` can get third argument: custom default value if key not in dict
(get {:a 0 :b 1} :c "not there!") ;; => "not there!"
;; otherwise `nil`
(get {:a 0 :b 1} :c) ;; => nil 

(:d dct-3 "not found") ;; => "not found"

;; nested get: `get-in`
(get {:a 0 :b {:c "this"}} [:b :c])    ;; => nil
(get-in {:a :b {:c "this"}} [:b :c])   ;; => "this"







;; `progn` in common lisp is in clojure `do`
;; more than one expressions
(do "expr1"
    "expr2"
    "expr3")
