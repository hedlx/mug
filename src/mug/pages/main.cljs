(ns mug.pages.main
  (:require [reagent.core :as reagent :refer [atom]]
            [mug.components.page :refer [page-skeleton]]))

(defn main-page []
  [page-skeleton
   [:div "Main page"]])
