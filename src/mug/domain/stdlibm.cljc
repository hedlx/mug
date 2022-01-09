(ns mug.domain.stdlibm)

(defn- fnt? [x] (-> x (meta) (get :fnt false)))
(defn signal-form [signal]
  (fn [maybe-fnt & rest]
    (let [opts (cons maybe-fnt rest)]
      (if (fnt? maybe-fnt)
        ((apply signal rest) maybe-fnt)
        (apply signal opts)))))

(defmacro defsig [name & body]
  (let [foo (cons 'fn body)]
    `(def ~name (signal-form ~foo))))
