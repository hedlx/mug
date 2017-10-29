(ns mug.pages.manual.playback
  (:require [mug.pages.manual.context :as context]
            [mug.domain.synth.core :as synth]
            [mug.pages.manual.eval :as eval])
  (:require-macros [mug.domain.eval.helper :as helper]))

(defn play [] (synth/play context/manual-context (:offset @context/manual-context)))
(defn stop [] (synth/stop context/manual-context))
(defn update-source [source] (eval/concrete-eval source))
