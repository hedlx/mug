(ns mug.components.playback.controls.component
  (:require [mug.components.buttons.icon_button.component :refer [play-button stop-button]]
            [reagent.core :as r]))

(defn- get-button [handle-start handle-stop state]
  (if (= state :started) [stop-button handle-stop] [play-button handle-start]))

(defn playback-controls [handle-start handle-stop]
  (let [state (r/atom :stopped)]
    (fn []
      [:div.playback-controls
       [:div.playback-button
        (get-button
         #(do (handle-start) (reset! state :started))
         #(do (handle-stop) (reset! state :stopped))
         @state)]
       [:div.playback-placeholder]])))
