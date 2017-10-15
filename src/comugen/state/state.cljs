(ns comugen.core.state
  (:require [reagent.core :as reagent :refer [atom]]))

(def app-state (reagent/atom {}))