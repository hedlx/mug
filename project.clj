(defproject mug "0.0.1"
  :description "Collaborative music synthesizer"
  :url "http://github.com/hedlx/mug"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/tools.namespace "0.3.0-alpha4"]
                 [org.clojure/java.classpath "0.2.3"]
                 [secretary "1.2.3"]
                 [reagent "0.7.0"]
                 [garden "1.3.2"]]

  :plugins [[lein-kibit "0.1.5"]
            [lein-garden "0.3.0"]
            [lein-figwheel "0.5.13"]
            [lein-cljfmt "0.5.7"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]
  :resource-paths ["resources"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "mug.core/on-js-reload"
                           :open-urls ["http://localhost:3449/"]}
                :compiler {:main mug.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/mug.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true

                           :foreign-libs [{:file "resources/public/js/npm-deps-bundle.js"
                                           :provides ["webpack.bundle"]}]
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :incremental true
                :compiler {:main "mug.core"
                           :output-to "resources/public/js/compiled/mug.js"
                           :foreign-libs [{:file "resources/public/js/npm-deps-bundle.js"
                                           :provides ["webpack.bundle"]}]

                           :optimizations :simple
                           :pretty-print false
                           :optimize-constants true
                           :static-fns true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.13"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}}

  :garden {:builds [{:source-paths ["src"]
                     :stylesheet mug.styles.core/styles
                     :compiler {:output-to "resources/public/css/core.css"
                                :vendors [:moz :webkit]
                                :pretty-print? false}}]})
