(defproject mug "0.0.1"
  :description "Collaborative music synthesizer"
  :url "http://github.com/hedlx/mug"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :min-lein-version "2.9.0"

  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.758"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/tools.namespace "1.2.0"]
                 [org.clojure/java.classpath "1.0.0"]
                 [reagent "1.1.0"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.8" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src" "env"]
  :resource-paths ["resources"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src" "env"]
                :figwheel {:on-jsload "mug.core/mount-root"
                           :open-urls ["http://localhost:3449/"]}
                :compiler {:main mug.dev
                           :asset-path "js/app"
                           :output-to "resources/public/js/app.js"
                           :output-dir "resources/public/js/app"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "prod"
                :source-paths ["src" "env/prod/cljs"]
                :incremental true
                :compiler {:main mug.prod
                           :output-to "resources/public/js/app.js"
                           :optimizations :simple
                           :pretty-print false
                           :optimize-constants true
                           :static-fns true}}]}

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.4"]
                                  [figwheel-sidecar "0.5.20"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/app"
                                                     :target-path]}})
