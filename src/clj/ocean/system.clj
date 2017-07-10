(ns ocean.system
  (:require
   [ocean.service                 :as service]
   [hellhound.components
    [websocket :as websocket]
    [pedestal  :as pedestal]]

   [hellhound.components          :as components]
   [hellhound.system              :refer [defsystem]]))

(def event-router {})
;; (defsystem dev-system
;;   (websocket/make-instance {:router event-router})
;;   (pedestal/make-instance #'service/service
;;                           {:requirements :websocket}))
(def dev-system
  {:components
   {:websocket (components/create-instance
                (websocket/new-websocket {:router event-router}))
    :pedestal  (components/create-instance
                (pedestal/new-pedestal #'service/service)
                [:websocket])}})
