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
