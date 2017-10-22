(ns mug.components.editor.editor
  (:require [reagent.core :as r]
            [webpack.bundle]))

(defn editor [program]
  (let [cm (aget js/window "deps" "cm")]
    (r/create-class
     {:component-did-mount
      #(let [codemirror (cm (r/dom-node %)
                            #js {:mode "clojure"
                                 :theme "gruvbox"
                                 :lineNumbers true
                                 :autoCloseBrackets true
                                 :matchBrackets true})]
         (.on codemirror "change"
              (fn []
                (let [updated-program (.getValue codemirror)]
                  (when-not (= updated-program @program)
                    (reset! program updated-program))))))

      :reagent-render
      (fn [program]
        [:div.editor])})))
