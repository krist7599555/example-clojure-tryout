#!/usr/bin/env clj -M
(ns main)

(def author "krist7599555")
(def primes '(2 3 5 7 11 13 15 17 19 23 29))
(defn hello [] "Hello, World!")

;; fibonucci lazy list
(defn fib
  ([] (fib 1 1))
  ([a b] (lazy-seq (cons a (fib b (+ a b))))))

;; curl to text Alice's Adventures in Wonderland by Lewis Carroll
;; (def book (slurp "https://www.gutenberg.org/files/11/11-0.txt"))
;; (println book)

(println (take 10 (fib)))

(def a `("a" "b" "c"))
(def b `("A" "B" "C"))
(println a)
(println (for [lhs a rhs b] (str lhs rhs)))

;; catalan with combinatoric method
(defn catalan [n]
  (if (<= n 0) (set [""])
      (if (= n 1) (set ["ab"])
          (->> (range n)
               (map #(for [lhs (catalan %)
                           rhs (catalan (- (- n %) 1))]
                       [(str "a" lhs "b" rhs)]))
               (flatten)
               (set)))))


;; catalan with counting recursive
(defn catalan2
  ([n] (catalan2 (* n 2) 0))
  ([len, up]
   (set
    (if (= len 0) [""]
        (concat
         (if (> up 0)   (for [nx (catalan2 (- len 1) (- up 1))]  (str "b" nx)) `())
         (if (> len up) (for [nx (catalan2 (- len 1) (+ up 1))]  (str "a" nx)) `()))))))


(def catalan3 (memoize catalan))

(doseq [i (range 10)]
  (let [res1 (catalan i) res2 (catalan2 i) res3 (catalan3 i)]
    (println "catalan" i "=" (count res1) "; is equal" (= res1 res2 res3))))


;; BASIC HELLO WORLD
;; (println author (hello))
;; (println "prime numbers =" primes)
