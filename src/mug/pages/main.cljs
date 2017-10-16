(ns mug.core.pages.main
    (:require [reagent.core :as reagent :refer [atom]]
              [mug.core.components.page :refer [page-skeleton]]))

(defn main-page []
  [page-skeleton
    [:div.main-content "Main page"]])
