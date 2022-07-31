# START CLOJURE

## Install

install [clojure](https://clojure.org/guides/install_clojure)
install [leiningen](https://leiningen.org) clojure package manager

```bash
# clojure
# clj
brew install clojure/tools/clojure
# lein (project structure)
brew install leiningen
```

# Run Standalone

```bash
clj -M ./src/main
```

# Run With REPL in VSCode

this project create with `lein`

- open VSCode
- goto specific file `.clj`
- open command prompt `cmd+p`
- `Calva: Start Project REPL and Connect aka (Jack in)` you will got repl window
- press `ctrl+enter` on speciific line to eval
- or `Calva: Load and Evaluate Currentfile and its' requires` to load all program

## Sample Code

```clojure
(defn catalan ^PersistentList [^Number n]
  (case n
    0 #{""}
    1 #{"ab"}
    (->> (for [i   (range n)
               lhs (catalan i)
               rhs (catalan (- n i 1))]
           (str "a" lhs "b" rhs))
         (set))))

(defn catalan2 ^PersistentList
  ([^Number n] (catalan2 (* n 2) 0))
  ([^Number len, ^Number up]
   (if (= len 0)
     (set `(""))
     (set (concat
           (if (> up 0)   (map #(str "b" %) (catalan2 (- len 1) (- up 1))) `())
           (if (> len up) (map #(str "a" %) (catalan2 (- len 1) (+ up 1))) `()))))))


(def book ^String (slurp "https://www.gutenberg.org/files/11/11-0.txt"))
(def common-word ^PersistentHashSet
  (->> (slurp "https://gist.githubusercontent.com/deekayen/4148741/raw/98d35708fa344717d8eee15d11987de6c8e26d7d/1-1000.txt")
       (str/lower-case)
       (re-seq #"\S+")
       (set)))

(def most-freq-word ^PersistentHashSet
  (->> (re-seq #"\S+" book)
       (map str/lower-case)
       (remove common-word)
       (frequencies)
       (sort-by val)
       (take-last 10)))
```
