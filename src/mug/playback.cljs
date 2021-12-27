(ns mug.playback
  (:require [mug.domain.synth :as synth]
            [mug.eval :as eval]
            [mug.context :as context]))

(defn play [] (synth/play context/manual-context (:offset @context/manual-context)))
(defn stop [] (synth/stop context/manual-context))
(defn update-source [source] (eval/concrete-eval source))
