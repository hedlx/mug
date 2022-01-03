(ns mug.core
  (:require [reagent.dom :as rd]
            [mug.root :as root]))

(defn mount-root []
   (rd/render [root/root]
             (.getElementById js/document "app")))

(defn init! []
  (mount-root))
