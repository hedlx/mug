(ns mug.components.buttons.icon.styles
  (:require [mug.styles.colors :as c]))


(def defaults
  [[:.icon-button {:display "flex"
                   :justify-content "center"
                   :align-items "center"
                   :width "40px"
                   :height "40px"
                   :border-radius "10px"

                   :cursor "pointer"}]
   [:.icon-button.green {:background-color c/green}]
   [:.icon-button.red {:background-color c/red}]])
