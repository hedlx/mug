(ns mug.core.router
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as reagent]
            [mug.pages.main :refer [main-page]]
            [mug.pages.manual :refer [manual-page]]
            [mug.core.state :refer [app-state]]))

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
