(ns mug.domain.eval
  (:require [cljs.js :refer [empty-state eval-str js-eval]]))

(defn- build-loader [sources]
  (fn [{:keys [name]} callback]
    (let [str-name (.-str name)
          source (sources str-name)]
      (if source
        (print "Loading " str-name)
        (print "Unable to load " str-name))
      (callback {:lang :clj :source (str source)}))))

(defonce eval-subs (atom {}))
(defn sub [s]
  (let [id (random-uuid)]
    (swap! eval-subs assoc id s)
    #(swap! eval-subs dissoc id)))

(defn build-eval [header sources footer]
  (let [current-state (empty-state)]
    (fn [source]
      (eval-str
       current-state
       (str header source footer)
       "user-fn"
       {:eval js-eval
        :load (build-loader sources)}
       (fn [r]
         (let [err (:error r)]
           (if (nil? err)
             (doseq [sub (vals @eval-subs)] (sub source))
             (print "eval error:" (:error r)))))))))
