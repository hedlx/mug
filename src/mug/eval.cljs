(ns mug.eval
  (:require [mug.domain.eval :as eval-core])
  (:require-macros [mug.domain.source :as helper])
  )

(def header
  (str
   '(ns mug.player
      (:require [mug.context :as c]
                [mug.domain.synth :as s]))))

(def footer
  (str '(when-not (nil? (resolve 'gen))
          (s/set-gen-fn c/manual-context gen))))

(def sources
  (helper/sources
   mug.context
   mug.domain.synth))

(def concrete-eval (eval-core/build-eval header sources footer))