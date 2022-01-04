(ns mug.playback
  (:require
   [reagent.core :as r]
   [mug.domain.synth :as synth]
   [mug.eval :as eval]
   [mug.context :as context]
   [mug.media_session :as sess]))

(defonce playing (r/atom false))

(defn stop [] (synth/stop context/manual-context) (reset! playing false) (sess/sess-paused!))
(defn play [] (.then (synth/play context/manual-context (:offset @context/manual-context))
                     (fn [] (reset! playing true) (sess/setup-media-session!) (sess/sess-playing!))))
(defn update-source [source] (eval/concrete-eval source))

(sess/set-callbacks! play stop)