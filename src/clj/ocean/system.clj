(ns ocean.system
  (:require
   [ocean.service                 :as service]
   [hellhound.components.pedestal  :as pedestal]
   [hellhound.components          :as components]
   [hellhound.system              :refer [defsystem]]))

(def event-router {})
;; (defsystem dev-system
;;   (websocket/make-instance {:router event-router})
;;   (pedestal/make-instance #'service/service
;;                           {:requirements :websocket}))
(def dev-system
  {:components
   {:pedestal  (components/create-instance
                (pedestal/new-pedestal service/service))}})
