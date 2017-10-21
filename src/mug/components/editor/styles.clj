(ns mug.components.editor.styles
  (:require [mug.styles.colors :as c]))

(def defaults
  [[:.editor {:-moz-appearance "textarea"
              :-webkit-appearance "textarea"

              :display "block"

              :height "100%"

              :background-color c/dark-bg
              :color c/dark-fg
              :font-family "'Fira Mono', monospace"
              :font-size "13px"
              :outline "0px solid transparent"

              :caret-color c/dark-fg

              :overflow "auto"
              :overflow-wrap "normal"
              :word-wrap "normal"}]])
