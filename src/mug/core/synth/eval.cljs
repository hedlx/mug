(ns mug.core.synth.eval
  (:require [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval-str js-eval *load-fn*]]
            [cljs.env :refer [*compiler*]])
  (:require-macros [mug.core.synth.macro :as macro]))

(def dependencies
  (macro/sources
   cljs-bach.synthesis
   mug.core.synth.synth))

(defn- loader
  [{:keys [name]} callback]
  (let [str-name (.-str name)
        source (dependencies str-name)]
    (if source
      (js/console.log (str "Loading " str-name "."))
      (js/console.log (str "Unable to load " str-name ".")))
    (callback {:lang :clj :source (str source)})))

(def source-deps
  (str
   '(ns mug.player
      (:require [mug.core.synth.synth :refer [play noise classic]]))))

; This one is necessary for testing purposes in future
(defn- print-cache [opts cb]
  (.log js/console opts)
  (cb {:value nil}))

(def state (empty-state))

(defn eval-user-synth [source]
  (eval-str state
            (str source-deps source)
            nil
            {:eval js-eval
             ; :cache-source print-cache
             :load loader}
            identity))
