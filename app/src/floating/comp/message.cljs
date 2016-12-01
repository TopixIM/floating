
(ns floating.comp.message
  (:require [respo.alias :refer [create-comp div input button]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-ui.style :as ui]))

(defn render [message] (fn [state mutate!] (div {} (comp-text (:text message) nil))))

(def comp-message (create-comp :message render))
