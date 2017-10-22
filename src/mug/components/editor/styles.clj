(ns mug.components.editor.styles
  (:require [mug.styles.colors :as c]))

(def defaults
  [[:.editor {:width "100%"
              :height "100%"
              :font-family "'Fira Mono', monospace"
              :font-size "12px"
              :color c/dark-fg}]
   [:.CodeMirror.cm-s-gruvbox {:background-color c/dark-bg
                               :color c/dark-fg}]
   [:.cm-s-gruvbox
    [:.CodeMirror-cursor {:border-left (str "1px solid " c/dark-fg)}]
    [:.CodeMirror-gutters {:background c/dark-bg
                           :border-right (str "1px dashed " c/dark-bg2)}]
    [:span.cm-def {:color c/red}]
    [:span.cm-keyword {:color c/blue}]
    [:span.cm-string {:color c/green}]
    [:span.cm-number {:color c/purple-br}]
    [:span.cm-atom {:color c/yellow}]
    [:span.cm-builtin {:color c/blue-br}]
    [:span.cm-variable {:color c/dark-fg2}]
    [:span.cm-bracket {:color c/gray}]
    [:span.cm-comment {:color c/dark-gray}]
    [:span.CodeMirror-nonmatchingbracket {:color c/red-br}]
    [:span.CodeMirror-matchingbracket {:color c/dark-fg0}]
    [:.CodeMirror-selected {:background-color c/dark-bg2}]]])
