(ns mug.domain.stdlib
  (:require [mug.domain.stdlibm])
  (:require-macros [mug.domain.stdlibm :refer [defsig]]))

(def rate (atom 8000))
(defn norm [t] (/ t @rate))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn byte->signal [x]
  (-> x
      (mod 256)
      (- 128)
      (/ 128)))
(defn unwrap [fnt] (fnt))

(defn- sine$ [freq shift fnt] 
  (let [nt (norm (unwrap fnt))]
    (Math/sin (+ (* 2 Math/PI nt freq) shift))))
#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defsig sine
  ([freq shift] (partial sine$ freq shift))
  ([freq] (sine freq 0)))

(defn- saw$ [freq shift fnt]
  (let [nt (norm (+ (unwrap fnt) shift))
        trunc (Math/trunc (+ 0.5 (* nt freq)))]
    (-> nt (* freq) (- trunc) (* 2))))
#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defsig saw
  ([freq shift] (partial saw$ freq shift))
  ([freq] (saw freq 0)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn gain
  ([value level] (* level value))
  ([level] #(* level %)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defsig add [& opts]
  (fn [fnt] (->> opts (map #(% fnt)) (reduce +))))

