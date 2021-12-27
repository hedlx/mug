(ns mug.editor
  (:require [reagent.core :as r]))

(def eInst (r/atom nil))

(defn editor [{on-change :on-change
               init-value :init-value}]
  [:div {:class "w-100"
         :ref (fn [el] (when (and (nil? @eInst) (not (nil? el)))
                         (let [inst (-> js/window
                                        (. -monaco)
                                        (. -editor)
                                        (. create el (clj->js {:value init-value
                                                               :language "clojure"
                                                               :minimap {:enabled false}})))]
                           (swap! eInst (fn [] inst))
                           (. inst onDidChangeModelContent #(on-change (. inst getValue))))))}])
