
(ns floating.comp.message
  (:require [respo.alias :refer [create-comp div input button]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.debug :refer [comp-debug]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [floating.comp.time :refer [comp-time]]))

(defn on-score [message-id] (fn [e dispatch!] (dispatch! :message/vote message-id)))

(def style-score
  {:color :white,
   :background-color colors/attractive,
   :width 32,
   :cursor :pointer,
   :border-radius "16px",
   :height 32})

(defn render [message]
  (fn [state mutate!]
    (div
     {:style ui/row}
     (div
      {:style (merge ui/center style-score), :event {:click (on-score (:id message))}}
      (comp-text (:score message) nil))
     (comp-space 16 nil)
     (div
      {}
      (div {} (comp-text (:text message) nil))
      (div
       {}
       (comp-text (get-in message [:user :name]) nil)
       (comp-space 8 nil)
       (comp-time (:time message)))))))

(def comp-message (create-comp :message render))
