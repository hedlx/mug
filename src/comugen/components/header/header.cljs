(ns comugen.core.components.header
  (:require [reagent.core :as reagent :refer [atom]]))

(defn header []
  [:div.header
    [:a.title {:href "/#/"} "Coμgen"]
    [:div.navigation
      [:a.navitem {:href "/#/manual"} "Manual"]]])
