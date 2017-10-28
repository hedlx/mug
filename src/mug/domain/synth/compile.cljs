(ns mug.domain.synth.compile
  (:require [mug.domain.eval.core :refer [build-eval]])
  (:require-macros [mug.domain.eval.helper :as helper]))

(def header
  (str
   '(ns mug.player
      (:require [mug.domain.synth.core :refer [play noise classic]]))))

(def sources
  (helper/sources
   cljs-bach.synthesis
   mug.domain.synth.core))

(def concrete-eval (build-eval header sources))

(defn eval [source] (concrete-eval source))
