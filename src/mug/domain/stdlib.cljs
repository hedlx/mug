(ns mug.domain.stdlib)

(def rate (atom 8000))
(defn norm [t] (/ t @rate))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn byte->signal [x]
  (-> x
      (mod 256)
      (- 128)
      (/ 128)))
(defn unwrap [fnt] (fnt))

(defn fnt? [x] (-> x (meta) (get :fnt false)))
(defn signal-form [signal]
  (fn [maybe-fnt & rest]
    (let [opts (cons maybe-fnt rest)]
      (if (fnt? maybe-fnt)
        ((apply signal rest) maybe-fnt)
        (apply signal opts)))))

(defn- sine$ [freq shift fnt] 
  (let [nt (norm (unwrap fnt))]
    (Math/sin (+ (* 2 Math/PI nt freq) shift))))
(defn- sine-
  ([freq shift] (partial sine$ freq shift))
  ([freq] (sine- freq 0)))
#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def sine (signal-form sine-))

(defn- saw$ [freq shift fnt]
  (let [nt (norm (+ (unwrap fnt) shift))
        trunc (Math/trunc (+ 0.5 (* nt freq)))]
    (* 2 (- (* nt freq) trunc))))
(defn- saw-
  ([freq shift] (partial saw$ freq shift))
  ([freq] (saw- freq 0)))
#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def saw (signal-form saw-))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn gain
  ([value level] (* level value))
  ([level] #(* level %)))

(defn- add- [& opts]
  (fn [fnt] (->> opts (map #(% fnt)) (reduce +))))
#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def add (signal-form add-))
