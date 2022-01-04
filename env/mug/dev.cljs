(ns ^:figwheel-no-load  mug.dev
  (:require
   [mug.core :as core]
   [devtools.core :as devtools]))

(devtools/install!)
(enable-console-print!)
(core/init!)
