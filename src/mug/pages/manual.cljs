(ns mug.pages.manual
  (:require [reagent.core :as r]
            [mug.components.page :refer [page-skeleton]]
            [mug.components.editor.editor :refer [editor]]
            [mug.components.playback.controls.controls :refer [playback-controls]]
            [mug.core.synth.eval :as se]))

(defn manual-page []
  (let [program (r/atom "")]
    (fn []
      [page-skeleton
       [:div.manual-page-content
        [:div.manual-page-controls
         [playback-controls #(se/eval-user-synth @program) #(println "stop")]]
        [:div.manual-page-content-editor-container
         [:div.manual-page-content-editor
          [editor program]]]]])))
