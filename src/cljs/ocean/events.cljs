(ns ocean.events
  (:require
   [re-frame.core        :as re-frame]
   [hellhound.connection :as channels]
   [hellhound.handlers.app-db]
   [ocean.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 :set-communication-state
 (fn [db [_ state]]
   (update-in db [:communication :state] (fn [_] state))))

(re-frame/reg-event-db
 :select-article
 (fn [db [_ id]]
   (update-in db [:selected-article] (fn [_] id))))
