(ns mug.domain.eval.core

  (:require [cljs.js :refer [empty-state eval-str js-eval]]
            [cljs.env :refer [*compiler*]]))

(defn- build-loader [sources]
  (fn [{:keys [name]} callback]
    (let [str-name (.-str name)
          source (sources str-name)]
      (if source
        (js/console.log (str "Loading " str-name "."))
        (js/console.log (str "Unable to load " str-name ".")))
      (callback {:lang :clj :source (str source)}))))

(defn build-eval [header sources]
  (let [current-state (empty-state)]
    (fn [source]
      (eval-str current-state
                (str header source)
                nil
                {:eval js-eval
                 :load (build-loader sources)}
                identity))))
