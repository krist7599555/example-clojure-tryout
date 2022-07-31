#!/usr/bin/env clj -M
(ns main)

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def author "krist7599555")
(def primes [2 3 5 7 11 13 15 17 19 23 29])

;; BASIC HELLO WORLD
(print author "\n")
(print "prime numbers =" primes)
