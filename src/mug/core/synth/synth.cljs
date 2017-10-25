(ns mug.core.synth.synth
  (:require [cljs-bach.synthesis :as b]))

(defonce context (b/audio-context))

(defn play
  ([synth duration]
   (let [node (b/connect-> synth b/destination)
         at (b/current-time context)]
     (b/run-with node context at duration)))
  ([synth duration gain]
   (play (b/connect-> synth (b/gain gain)) duration)))

(defn raw-buffer [generate-bit! context duration sample-rate]
  (let [frame-count (* sample-rate duration)
        buffer (.createBuffer context 1 frame-count sample-rate)
        data (.getChannelData buffer 0)]
    (doseq [i (range frame-count)] (aset data i (generate-bit! i)))
    buffer))

(def buffer (memoize raw-buffer))

(defn noise [generate-bit! sample-rate]
  (fn [context at duration]
    (b/source
     (doto (.createBufferSource context)
       (-> .-buffer (set! (buffer generate-bit! context duration sample-rate)))
       (.start at)))))

(defn classic [x]
  (-> (bit-and (* x x)
               (bit-shift-right x 6)
               (bit-shift-right x 12))
      (mod 256)
      (- 128)
      (/ 128)))
