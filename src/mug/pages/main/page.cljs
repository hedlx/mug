(ns mug.pages.main.page
  (:require [reagent.core :as reagent :refer [atom]]
            [mug.components.page_skeleton.component :refer [page-skeleton]]))

(defn main-page []
  [page-skeleton
   [:div "Main page"]])
