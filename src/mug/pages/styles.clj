(ns mug.pages.styles)

(def defaults
  [[:.manual-page-content {:display "flex"
                           :flex-direction "column"
                           :justify-content "center"
                           :align-items "flex-start"
                           :height "100%"
                           :width "100%"}]
   [:.manual-page-controls {:flex-grow "0"
                            :height "40px"
                            :width "100%"
                            :padding-bottom "10px"}]
   [:.manual-page-content-editor-container {:position "relative"
                                            :flex-grow "1"
                                            :height "100%"
                                            :width "100%"}]
   [:.manual-page-content-editor {:position "absolute"
                                  :left "0"
                                  :right "0"
                                  :top "0"
                                  :bottom "0"}]])
