(ns mug.pages.manual.context
  (:require [mug.domain.synth.core :as synth]))

(defonce manual-context (synth/empty-audio-context))
