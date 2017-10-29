(ns mug.styles.core
  (:require [garden.def :refer [defstyles]]
            [mug.styles.colors :as c]
            [mug.components.header.styles]
            [mug.components.page_skeleton.styles]
            [mug.components.editor.styles]
            [mug.components.buttons.icon_button.styles]
            [mug.components.playback.controls.styles]
            [mug.pages.manual.styles]))

(def defaults
  [[:html :body {:height "100%"
                 :background-color c/default-bg}]
   [:body
    :div
    :dl
    :dt
    :dd
    :ul
    :ol
    :li
    :h1
    :h2
    :h3
    :h4
    :h5
    :h6
    :hr
    :pre
    :form
    :fieldset
    :input
    :button
    :textarea
    :p
    :blockquote
    :th
    :td {:margin 0
         :padding 0}]
   [:a {:text-decoration "none"}]
   [:table {:border-collapse "collapse"
            :border-spacing 0}]
   [:fieldset :img {:border 0}]
   [:ol :ul {:list-style "none"}]
   [:caption :th {:text-align "left"}]
   [:h1 :h1 :h3 :h4 :h5 :h6 {:font-size "100%"
                             :font-weigth "normal"}]
   [:.height-zero {:height "0"}]
   [:.width-zero {:width "0"}]
   [:#app {:height "100%"
           :width "100%"
           :font-family "'Fira Sans', sans-serif"
           :color c/default-fg}]])

(defstyles styles
  (concat defaults
          mug.components.header.styles/defaults
          mug.components.page_skeleton.styles/defaults
          mug.components.editor.styles/defaults
          mug.components.buttons.icon_button.styles/defaults
          mug.components.playback.controls.styles/defaults
          mug.pages.manual.styles/defaults))
