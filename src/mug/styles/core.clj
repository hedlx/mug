(ns mug.styles.core
  (:require [garden.def :refer [defstyles]]
            [mug.components.header.styles]
            [mug.components.page.styles]))

(def defaults
  [[:html :body {:height "100%"}]
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
    :td {:margin  0
         :padding 0}]
   [:table {:border-collapse "collapse"
            :border-spacing  0}]
   [:fieldset :img {:border 0}]
   [:ol :ul {:list-style "none"}]
   [:caption :th {:text-align "left"}]
   [:h1 :h1 :h3 :h4 :h5 :h6 {:font-size   "100%"
                             :font-weigth "normal"}]
   [:* {:font-family "'Open Sans', sans-serif"}]

   [:.app {:height "100%"
           :width  "100%"}]])

(defstyles styles
           (concat defaults
                   mug.components.header.styles/defaults
                   mug.components.page.styles/defaults))
