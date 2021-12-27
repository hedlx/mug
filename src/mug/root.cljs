(ns mug.root
  (:require [reagent.core :as r]
            [goog.functions :as gfuncs]
            [mug.editor :as e]
            [mug.playback :as pb]))

(defn root []
  (let [playing (r/atom false)]
    (fn []
      [:div {:class "flex flex-column vh-100 w-100"}
       [:div {:class "h3 ph3 flex items-center justify-between"}
        [:div {:class "f2 b code"} "μG"]
        [:div {:class "f2 b code pointer"
               :on-click (fn []
                           (if @playing (pb/stop) (pb/play))
                           (swap! playing not))}
         (if @playing "stop" "play")]]
       [:div {:class "h-100 flex ma3 br4 shadow-4 overflow-hidden"}
        [e/editor {:on-change (gfuncs/debounce pb/update-source 500)
                   :init-value (str '(defn gen [x]
                                       (bit-and (* x x)
                                                (bit-shift-right x 6)
                                                (bit-shift-right x 12))))}]]])))
