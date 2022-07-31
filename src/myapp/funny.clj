#!/usr/bin/env clj -M
(ns myapp.funny
  (:import clojure.lang.LazySeq clojure.lang.PersistentList clojure.lang.PersistentHashSet))

(require '[clojure.string :as str])
(require '[clojure.pprint])

;; fibonucci lazy list
(defn fib ^LazySeq
  ([] (fib 1 1))
  ([a b] (lazy-seq (cons a (fib b (+ a b))))))

;; curl to text Alice's Adventures in Wonderland by Lewis Carroll
(def book ^String (slurp "https://www.gutenberg.org/files/11/11-0.txt"))
(def common-word ^PersistentHashSet
  (->> (slurp "https://gist.githubusercontent.com/deekayen/4148741/raw/98d35708fa344717d8eee15d11987de6c8e26d7d/1-1000.txt")
       (str/lower-case)
       (re-seq #"\S+")
       (set)))
(take 40 common-word)
(clojure.pprint/pp)

;; (println book)
(def most-freq-word ^PersistentHashSet
  (->> (re-seq #"\S+" book)
       (map str/lower-case)
       (remove common-word)
       (frequencies)
       (sort-by val)
       (take-last 10)))
most-freq-word
(clojure.pprint/pp)

(take 45 (fib))

;; catalan with combinatoric method
(defn catalan ^PersistentList [^Number n]
  (if (<= n 0) (set [""])
      (if (= n 1) (set ["ab"])
          (->> (range n)
               (map #(for [lhs (catalan %)
                           rhs (catalan (- (- n %) 1))]
                       [(str "a" lhs "b" rhs)]))
               (flatten)
               (set)))))


;; catalan with counting recursive
(defn catalan2 ^PersistentList
  ([^Number n] (catalan2 (* n 2) 0))
  ([^Number len, ^Number up]
   (set
    (if (= len 0) [""]
        (concat
         (if (> up 0)   (for [nx (catalan2 (- len 1) (- up 1))]  (str "b" nx)) `())
         (if (> len up) (for [nx (catalan2 (- len 1) (+ up 1))]  (str "a" nx)) `()))))))


(def catalan3 (memoize catalan))

(doseq [i (range 10)]
  (let [res1 (catalan i) res2 (catalan2 i) res3 (catalan3 i)]
    (println "catalan" i "=" (count res1) "; is equal" (= res1 res2 res3))))

