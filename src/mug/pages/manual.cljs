(ns mug.pages.manual
    (:require [reagent.core :as reagent :refer [atom]]
              [mug.components.page :refer [page-skeleton]]))

(defn manual-page []
  [page-skeleton
    [:div "Manual page"]])
