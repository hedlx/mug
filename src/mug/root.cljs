(ns mug.root
  (:require [goog.functions :as gfuncs]
            [mug.editor :as e]
            [mug.playback :as pb]
            [mug.storage :refer [get-symph]]))

(defn root []
  [:div {:class "bg-near-white flex flex-column vh-100 w-100"}
   [:div {:class "h3 ph5-l ph4-ns pv4 ph2 flex items-center justify-between"}
    [:div {:class "f2 b code"} "μG"]
    [:div {:class "f2 b code pointer"
           :style {:user-select "none"}
           :on-click #(if @pb/playing (pb/stop) (pb/play))}
     (if @pb/playing "stop" "play")]]
   [:div {:class "h-100 flex mh4-m mh5-l mb4-m mb5-l br4-ns overflow-hidden"
          :style {:box-shadow "rgb(38, 57, 77) 0px 20px 30px -10px"}}
    [e/editor {:on-change (gfuncs/debounce pb/update-source 500)
               :init-value (get-symph)}]]])
