(ns mug.domain.context)

(defn empty-audio-context []
  (let [rate 8000
        buffer-size (/ rate 4)]
    (atom {:gen-fn (fn [_] 0)
           :ctx nil
           :rate rate
           :buffer-size buffer-size
           :offset 0
           :node nil
           :gain 1})))
