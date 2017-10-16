(ns mug.core.pages.manual
    (:require [reagent.core :as reagent :refer [atom]]
              [mug.core.components.page :refer [page-skeleton]]))

(defn manual-page []
  [page-skeleton
    [:div.manual-content "Manual page"]])
