(ns mug.domain.stdlib)

(defn byte->float [x]
  (-> x
      (mod 256)
      (- 128)
      (/ 128)))
