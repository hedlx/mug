(ns mug.prod
  (:require [mug.core :as core]))

(set! *print-fn* (fn [& _]))

(core/init!)
