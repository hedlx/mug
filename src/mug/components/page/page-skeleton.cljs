(ns mug.components.page
  (:require [reagent.core :as reagent :refer [atom]]
            [mug.core.components.header :refer [header]]))

(defn page-skeleton [content]
  [:div.page
   [:div.page-row.height-zero [:div.page-cell [header]]]
   [:div.page-row [:div.page-cell
                   [:div.page-content content]]]])
