(ns mug.components.playback.controls.styles
  (:require [mug.styles.colors :as c]))

(def defaults
  [[:.playback-controls {:display "flex"
                         :justify-content "flex-start"
                         :align-items "center"
                         :height "100%"}]
   [:.playback-button {:flex-grow "0"}]
   [:.playback-placeholder {:flex-grow "1"
                            :width "100%"
                            :height "100%"
                            :margin-left "20px"
                            :border-radius "10px"
                            :background-color c/orange}]])
