(ns ocean.system
  (:require
   [ocean.service                 :as service]
   [hellhound.components
    [websocket :as websocket]
    [pedestal  :as pedestal]]

   [hellhound.system              :refer [defsystem]]))

(def event-router {})
(defsystem dev-system
  (websocket/make-instance {:router event-router})
  (pedestal/make-instance #'service/service))
