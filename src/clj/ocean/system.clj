(ns ocean.system
  (:require
   [ocean.service                :as service]
   [hellhound.component.pedestal :as pedestal]
   [hellhound.system             :refer [defsystem]]))

(defsystem dev-system
  (pedestal/make-pedestal-component service/service))
