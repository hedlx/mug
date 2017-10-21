(ns mug.components.header.styles
  (:require [mug.styles.colors :as c]))

(def defaults
  [[:.header {:display "flex"
              :align-items "center"
              :padding "0px 15px"
              :background-color c/header-bg
              :height "100%"
              :border-bottom (str "2px solid" c/header-bg-hover)}]
   [:.header-title {:font-weight 700
                    :font-size "20px"

                    :color c/default-fg}]
   [:.header-navigation {:padding-left "20px"}]
   [:.header-navigation-item {:padding "10px"
                              :color c/default-fg}]
   [:.header-navigation-item:hover {:background-color c/header-bg-hover}]])
