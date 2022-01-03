(ns mug.symphony)

(def symphony
  (str '(defn gen [x]
          (bit-and (* x x)
                   (bit-shift-right x 6)
                   (bit-shift-right x 12)))))