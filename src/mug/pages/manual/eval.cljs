(ns mug.pages.manual.eval
  (:require [mug.domain.eval.core :as eval-core]
            [mug.domain.synth.core :as s])
  (:require-macros [mug.domain.eval.helper :as helper]))

(def header
  (str
   '(ns mug.player
      (:require [mug.domain.synth.core :as s]
                [mug.pages.manual.context :as c]))))

(def footer
  (str '(when-not (nil? (resolve 'gen))
          (s/set-gen-fn c/manual-context gen))))

(def sources
  (helper/sources
   mug.domain.synth.core
   mug.pages.manual.context))

(def concrete-eval (eval-core/build-eval header sources footer))
