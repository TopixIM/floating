
(ns floating.comp.draft
  (:require [respo.alias :refer [create-comp div span textarea button]]
            [respo-ui.style :as ui]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]))

(defn on-input [mutate!] (fn [e dispatch!] (mutate! (:value e))))

(defn update-state [state text] text)

(defn on-send [text mutate!] (fn [e dispatch!] (dispatch! :message/add text) (mutate! "")))

(def style-toolbar {:justify-content :flex-end})

(defn init-state [& args] "")

(def style-text {:width "100%", :display :block})

(defn render []
  (fn [state mutate!]
    (div
     {:style {:width "100%"}}
     (textarea
      {:style (merge ui/textarea style-text),
       :event {:input (on-input mutate!)},
       :attrs {:placeholder "New message here...", :value state}})
     (comp-space nil 16)
     (div
      {:style (merge ui/row style-toolbar)}
      (button
       {:style ui/button, :event {:click (on-send state mutate!)}}
       (comp-text "Add" nil))))))

(def comp-draft (create-comp :draft init-state update-state render))
