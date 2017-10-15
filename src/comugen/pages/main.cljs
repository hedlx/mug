(ns mugenator.core.pages.main
    (:require [reagent.core :as reagent :refer [atom]]
              [mugenator.core.components.page :refer [page-skeleton]]))

(defn main-page []
  [page-skeleton
    [:div.main-content "Main page"]])
