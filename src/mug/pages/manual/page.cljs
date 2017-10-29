(ns mug.pages.manual.page
  (:require [reagent.core :as r]
            [goog.functions :as gfuncs]
            [mug.components.page_skeleton.component :refer [page-skeleton]]
            [mug.components.editor.component :refer [editor]]
            [mug.components.playback.controls.component :refer [playback-controls]]
            [mug.pages.manual.playback :as pb]))

(def debounce-timeout 500)

(defn manual-page []
  (let [source (r/atom "")]
    (fn []
      [page-skeleton
       [:div.manual-page-content
        [:div.manual-page-controls
         [playback-controls pb/play pb/stop]]
        [:div.manual-page-content-editor-container
         [:div.manual-page-content-editor
          [editor source (gfuncs/debounce pb/update-source debounce-timeout)]]]]])))
