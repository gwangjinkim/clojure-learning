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

;; `progn` in common lisp is in clojure `do`
;; more than one expressions
(do "expr1"
    "expr2"
    "expr3")



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



;; lists
(list 1 "two" {3 4})
'(1 2 3 4)

(nth '(:a :b :c) 0) ;; => :a
(nth '(:a :b :c) 2) ;; => :c

(cons 4 '(1 2 3))   ;; => '(4 1 2 3)
(conj 4 '(1 2 3))   ;; => '(4 1 2 3)
(rest '(1 2 3))
;; first
;; second
;; last

;; vectors    "faster accessible lists"

(vector "a" "b" "c")
["a" "b" "c"]

(get [1 2 3] 0)  ;; => 1
(get ["a" {:name "A. Berg"} "c"] 1)  ;; => {:name "Pugsley Winterbottom"}

;; cons adds at beginning, conj - for vectors - adds at end
(cons 0 [1 2 3]) ;; => [0 1 2 3]
(conj 0 [1 2 3]) ;; => [1 2 3 0]   


;; sets
(hash-set 1 1 2 2 3) ;; => #{1 2 3}
#{:a :a 2 2 2 3} ;; duplicate key
#{:a 2 2 3 3 3} ;; duplicate key

(cons :d #{:a :b :c}) ;; => #{:d :a :b :c}
(conj #{:a :b} :b)  ;; => #{:a :b}
(rest #{:a :b :c}) ;; => (:b :a)

(contains? #{:a :b} :a) ;; => true
(contains? #{:a :b} :c) ;; => false
(contains? #{nil} nil)  ;; => true

;; get or accessor function return obj if in set else nil
;; confusing when obj is `nil` ... => contains? is better!
(get #{:a :b} :a)       ;; => :a
(:a #{:a :b})           ;; => :a
;; however:
(get #{:a :b nil} nil)   ;; => nil
(get #{:a :b :d} :c) ;; => nil





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(inc 1.1) ;; => 2. 1
(map inc [0 1 2 3]) ;; => '(1 2 3 4)
;; map always returns a list!


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; define a function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn func-name
    "docstring"
    [] ;; parameter-'list' is a vector in clojure
    println("Functionbody")
    println("with implicite `do`"))

;; define multi-arity function
(defn multi-arity
    "Multi-arity function"
    ([arg-1 arg-2 arg-3]
     (do-things arg-1 arg-2 arg-3))
    ([arg-1 arg-3]
     (do-things arg-1 arg-3))
    ([arg-1 arg-2]
     (do-things arg-1 arg2))
    ([arg-1]
     (do-things arg-1))
    ([]
     (do-things)))

;; [a & things] ;;=> things binds rest of the args in a vector

;; destructuring: concisely bind names to values within a collection
(defn my-first
    [[first-thing]] ; first-thing within vector captured
    first-thing)

(defn first-and-rest
    [[first-thing & rest-things]]
    [first-thing rest-things])
;; (first-and-rest '(a b c d e f)) => [a (b c d e f)]

;; keywords
(defn announce-position
    [{x :x y :y}]
    (println (str "The position is x: " x " and y: " y ".")))
(announce-position {:x 1 :y 2})
;; => The position is x: 1 and y: 2.
;; => nil

;; more shortcut:
(defn announce-position
    [{:keys [x y]}]
    (println (str "The position is x: " x " and y: " y ".")))


;; anonymous functions
;; instead of parameterlist naming, use if just one arg: % else: %1, %2, ... and for rest: %&
;; save the (fn [...] body) and write #(body) using inside body the % place holders

(#(identity %&) 1 "blarg" :yip)
;; => (1 "blarg" :yip)

;; returning functions
(defn inc-maker
    "Create a custom incrementer"
    [inc-by]
    #(+ % inc-by))

(def inc3 (ink-maker 3))

(inc3 7) ;;=> 10



;; optional arguments and optional values?



