(ns comugen.core.components.page
  (:require [reagent.core :as reagent :refer [atom]]
            [comugen.core.components.header :refer [header]]))

(defn page-skeleton [content]
  [:div.page
    [:div.page-row [:div.page-cell [header]]]
    [:div.page-row [:div.page-cell
          [:div.page-content content]]]])