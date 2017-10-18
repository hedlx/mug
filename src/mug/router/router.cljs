(ns mug.core.router
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
    [secretary.core :as secretary]
    [goog.events :as events]
    [goog.history.EventType :as EventType]
    [reagent.core :as reagent]
    [mug.core.pages.main :refer [main-page]]
    [mug.core.pages.manual :refer [manual-page]]
    [mug.core.state :refer [app-state]]
    [accountant.core :as accountant]))

(defn app-routes []
  (defroute "/" [] (swap! app-state assoc :page :main))
  (defroute "/manual" [] (swap! app-state assoc :page :manual))
  (accountant/configure-navigation!
    {:nav-handler #(secretary/dispatch! %)
     :path-exists? #(secretary/locate-route %)})
  (accountant/dispatch-current!))

(defmulti current-page #(@app-state :page))
(defmethod current-page :main [] [main-page])
(defmethod current-page :manual [] [manual-page])
(defmethod current-page :default [] [:div "404"])
