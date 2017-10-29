(ns mug.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [reagent.core :as r]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [mug.pages.main.page :refer [main-page]]
            [mug.pages.manual.page :refer [manual-page]]))

(enable-console-print!)

(def app-state (r/atom {}))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  (defroute "/" [] (swap! app-state assoc :page :main))
  (defroute "/manual" [] (swap! app-state assoc :page :manual))
  (hook-browser-navigation!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :main [] [main-page])
(defmethod current-page :manual [] [manual-page])
(defmethod current-page :default [] [:div "404"])

((defn main []
   (app-routes)
   (r/render [current-page]
             (.getElementById js/document "app"))))
