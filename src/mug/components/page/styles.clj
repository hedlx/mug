(ns mug.components.page.styles)

(def defaults
  [[:.page {:display "table"
            :width "100%"
            :height "100%"}]
   [:.page-row {:display "table-row"
                :width "100%"}]
   [:.page-row.header {:height "40px"}]
   [:.page-cell {:display "table-cell"
                 :width "100%"}]
   [:.page-content {:height "100%"
                    :padding "15px"}]]) ; TODO: get rid of this padding
