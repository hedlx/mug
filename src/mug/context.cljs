(ns mug.context
  (:require [mug.domain.synth :as synth]))

(defonce manual-context (synth/empty-audio-context))
