(ns mug.components.editor.editor
  (:require [reagent.core :as r]))

(defn- update-editable []
  (.execCommand js/document "styleWithCSS" false true)
  (.execCommand js/document "foreColor" false "#ebdbb2")    ; TODO: somehow use color constant
  (.execCommand js/document "fontName" false "Fira Mono")
  (.execCommand js/document "fontSize" false "2"))

(defn- reset-editable []
  (.execCommand js/document "styleWithCSS" false false)
  (.execCommand js/document "removeFormat" false ""))

(defn- handle-event [program]
  (fn [e]
    (let [data (-> e .-target .-outerText)]
      (reset! program data)
      (update-editable))))

(defn- render [program]
  (update-editable)
  [:div.editor {:content-editable true
                :spell-check false
                :on-input (handle-event program)}])

(defn editor [program]
  (r/create-class
    {:component-did-mount update-editable
     :component-will-unmount reset-editable
     :reagent-render render}))
