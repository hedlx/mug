(ns mugenator.core
    (:require [reagent.core :as reagent :refer [atom]]
              [mugenator.core.router :refer [app-routes current-page]]))

(enable-console-print!)

((defn main []
  (app-routes)
  (reagent/render [current-page]
                  (.getElementById js/document "app"))))
