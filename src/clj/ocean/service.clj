(ns ocean.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [clojure.java.io :as io]
            [hellhound.http.route :as router]
            [hellhound.http.static :as static]
            [hellhound.service     :as hh-service]
            [ocean.service :as service]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (let [res (io/resource "public/index.html")]
    {:status 200
     :body (slurp res)
     :headers {"Content-Type" "text/html"}}))

;; Defines "/" and "/about" routes with their associated :get handlers.
;; The interceptors defined after the verb map (e.g., {:get home-page}
;; apply to / and its children (/about).
(def common-interceptors [(static/serve-resource {}) (body-params/body-params) http/html-body])

;; Tabular routes
;; (def routes #{["/" :get (conj common-interceptors `home-page)]
;;               ["/about" :get (conj common-interceptors `about-page)]})

(router/defroutes routes
  #{["/" :get (conj common-interceptors `home-page)]
    ["/about" :get (conj common-interceptors `about-page)]})

;; Map-based routes
;(def routes `{"/" {:interceptors [(body-params/body-params) http/html-body]
;                   :get home-page
;                   "/about" {:get about-page}}})

;; Terse/Vector-based routes
;(def routes
;  `[[["/" {:get home-page}
;      ^:interceptors [(body-params/body-params) http/html-body]
;      ["/about" {:get about-page}]]]])


;; Consumed by ocean.server/create-server
;; See http/default-interceptors for additional options you can configure
;;(def service (hh-service/create-service-map {::http/routes routes}))
(hh-service/defservice-map service {::http/routes routes})
;; You can bring your own non-default interceptors. Make
;; sure you include routing and set it up right for
;; dev-mode. If you do, many other keys for configuring
;; default interceptors will be ignored.
;; ::http/interceptors []



;;h Uncomment next line to enable CORS support, add
;; string(s) specifying scheme, host and port for
;; allowed source(s):
;;
;; "http://localhost:8080"
;;
;;::http/allowed-origins ["scheme://host:port"]
