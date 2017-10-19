(ns mug.components.header.styles)

(def defaults
  [[:.header {:display       "flex"
              :position      "static"
              :align-items   "center"
              :padding       "10px"
              :border-bottom "1px solid black"}]
   [:.header-title {:font-weight 700
                    :font-size   "20px"}]
   [:.header-navigation {:padding-left "20px"}]
   [:.header-navigation-item {:border  "1px dotted darkkhaki"
                              :padding "10px"}]])
