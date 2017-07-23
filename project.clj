(defproject ocean "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :exclusions [ch.qos.logback/logback-classic]
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.671"]

                 [codamic/hellhound   "1.0.0-SNAPSHOT"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"]

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :resource-paths ["config", "resources"]
  ;; If you use HTTP/2 or ALPN, use the java-agent to pull in the correct alpn-boot dependency
  ;:java-agents [[org.mortbay.jetty.alpn/jetty-alpn-agent "2.0.5"]]
  :profiles
  {:uberjar {:aot [ocean.server]}
   :dev
   {:aliases {"run-dev" ["trampoline" "run" "-m" "ocean.server/run-dev"]}
    :dependencies [[binaryage/devtools "0.9.4"]
                   [figwheel-sidecar "0.5.10"]
                   [com.cemerick/piggieback "0.2.1"]
                   [io.pedestal/pedestal.service-tools "0.5.2"]]

    :plugins      [[lein-figwheel "0.5.10"]
                   [lein-doo "0.1.7"]]}}

  :figwheel {:css-dirs ["resources/public/css"]}


  :cljsbuild
  {:builds
   [{:id            "dev"
     :source-paths ["src/cljs" "checkouts/hellhound/src/cljs/"]
     :figwheel     {:on-jsload "ocean.core/mount-root"
                    :open-urls ["http://localhost:3000/"]}
     :compiler     {:main                 ocean.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}


    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            ocean.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          ocean.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]}


  :main ^{:skip-aot true} ocean.server)
