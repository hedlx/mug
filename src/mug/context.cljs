(ns mug.context
  (:require [mug.domain.context :as context]))

(defonce manual-context (context/empty-audio-context))
