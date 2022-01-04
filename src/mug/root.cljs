(ns mug.root
  (:require [goog.functions :as gfuncs]
            [mug.editor :as e]
            [mug.playback :as pb]
            [mug.symphony :refer [symphony]]))

(defn root []
  [:div {:class "flex flex-column vh-100 w-100"}
   [:div {:class "h3 ph3 flex items-center justify-between"}
    [:div {:class "f2 b code"} "μG"]
    [:div {:class "f2 b code pointer"
           :style {:user-select "none"}
           :on-click #(if @pb/playing (pb/stop) (pb/play))}
     (if @pb/playing "stop" "play")]]
   [:div {:class "h-100 flex ma3 br4 shadow-4 overflow-hidden"}
    [e/editor {:on-change (gfuncs/debounce pb/update-source 500)
               :init-value symphony}]]])
