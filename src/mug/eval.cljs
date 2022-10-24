(ns mug.eval
  (:require [mug.domain.eval :as eval-core]
            [mug.storage :refer [get-symph]])
  (:require-macros [mug.domain.source :as helper]))

(def header
  (str
   '(ns mug.player
      (:require [mug.context :refer [manual-context]]
                [mug.domain.stdlib :as std]
                [mug.domain.stdlibm])
      (:require-macros [mug.domain.stdlibm :refer [defsig]]))))

(def footer
  (str '(when-not (nil? (resolve 'gen))
          (swap! manual-context assoc :gen-fn gen))))

(def sources
  ;; That is kinda okay
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (helper/sources
   mug.context
   mug.domain.context
   mug.domain.stdlibm
   mug.domain.stdlib))

(def concrete-eval (eval-core/build-eval header sources footer))

(js/setTimeout #(concrete-eval (get-symph)) 100)
