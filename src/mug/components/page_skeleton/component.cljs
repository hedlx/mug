(ns mug.components.page_skeleton.component
  (:require [reagent.core :as reagent :refer [atom]]
            [mug.components.header.component :refer [header]]))

(defn page-skeleton [content]
  [:div.page
   [:div.page-row.header [:div.page-cell [header]]]
   [:div.page-row [:div.page-cell
                   [:div.page-content content]]]])
