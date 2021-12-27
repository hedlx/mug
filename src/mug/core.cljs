(ns mug.core
  (:require [reagent.dom :as rd]
            [mug.root :as root]
            [webpack.bundle]))

(defn mount-root []
   (rd/render [root/root]
             (.getElementById js/document "app")))

(defn init! []
  (mount-root))
