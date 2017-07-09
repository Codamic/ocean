(ns ocean.core
  (:require
   [hellhound.core :as hellhound]
   [ocean.routes   :as router]
   [ocean.views    :as views]
   [ocean.events   :as events]
   [ocean.subs     :as subs]))


;; Your development environment setup goes here
(defn dev-setup [])



(defn ^:export init []
  (hellhound/init!
   {:router           (router/app-routes)
    :dispatch-events  [:initialize-db]
    :dev-setup        dev-setup
    :main-view        views/main-panel}))
