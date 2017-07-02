(ns ocean.system
  (:require
   [ocean.service                 :as service]
   [hellhound.components.pedestal :as pedestal]
   [hellhound.system              :refer [defsystem]]))

(defsystem dev-system
  (websocket/make-instanc)
  (pedestal/make-instance service/service))
