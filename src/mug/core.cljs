(ns mug.core
  (:require [reagent.core :as r]
            [mug.core.router :refer [app-routes current-page]]))

(enable-console-print!)

((defn main []
   (app-routes)
   (r/render [current-page]
             (.getElementById js/document "app"))))
