
(ns floating.core
  (:require [respo.core :refer [render! clear-cache!]]
            [floating.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [floating.network :refer [send! setup-socket!]]))

(defn dispatch! [op op-data] (send! op op-data))

(defonce store-ref (atom {}))

(defonce states-ref (atom {}))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @store-ref) target dispatch! states-ref)))

(defn on-jsload [] (clear-cache!) (render-app!) (println "code updated."))

(defn -main []
  (enable-console-print!)
  (render-app!)
  (setup-socket!
   store-ref
   {:on-close! (fn [event] (.error js/console "Lost connection!")),
    :url (str "ws://" (.-hostname js/location) ":5023")})
  (add-watch store-ref :changes render-app!)
  (add-watch states-ref :changes render-app!)
  (println "app started!"))

(set! js/window.onload -main)
