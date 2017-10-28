(ns mug.pages.manual
  (:require [reagent.core :as r]
            [mug.components.page_skeleton.component :refer [page-skeleton]]
            [mug.components.editor.component :refer [editor]]
            [mug.components.playback.controls.component :refer [playback-controls]]
            [mug.domain.synth.compile :refer [eval]]))

(defn manual-page []
  (let [program (r/atom "")]
    (fn []
      [page-skeleton
       [:div.manual-page-content
        [:div.manual-page-controls
         [playback-controls #(eval @program) #(println "stop")]]
        [:div.manual-page-content-editor-container
         [:div.manual-page-content-editor
          [editor program]]]]])))
