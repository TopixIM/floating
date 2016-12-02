
(ns floating.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.alias :refer [create-comp div span]]
            [respo.comp.debug :refer [comp-debug]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-code comp-text]]
            [floating.comp.home :refer [comp-home]]
            [respo-message.comp.msg-list :refer [comp-msg-list]]))

(defn on-add [e dispatch!]
  (dispatch! :router/change {:router nil, :name :add, :title "Add", :data {}}))

(defn on-new [e dispatch!]
  (dispatch! :router/change {:router nil, :name :new, :title "New", :data {}}))

(defn on-profile [e dispatch!]
  (dispatch! :router/change {:router nil, :name :profile, :title "Profile", :data {}}))

(def style-header
  {:color :white,
   :font-size 16,
   :background-color colors/motif,
   :padding "0 16px",
   :justify-content :space-between,
   :height 48})

(def style-body {})

(defn on-hot [e dispatch!]
  (dispatch! :router/change {:router nil, :name :hot, :title "Hot", :data {}}))

(def style-pointer {:cursor "pointer"})

(defn render [store]
  (fn [state mutate!]
    (div
     {:style (merge ui/global ui/fullscreen ui/column)}
     (div
      {:style (merge ui/row-center style-header)}
      (div
       {:style ui/row}
       (div {:event {:click on-hot}} (comp-text "Hot" nil))
       (comp-space 16 nil)
       (div {:event {:click on-new}} (comp-text "New" nil)))
      (if (:logged-in? store)
        (div
         {:style ui/row}
         (div {:style style-pointer, :event {:click on-add}} (comp-text "Add" nil))
         (comp-space 16 nil)
         (div {:style style-pointer, :event {:click on-profile}} (comp-text "Me" nil)))
        (div {} (comp-text "Guest" nil))))
     (div {:style style-body} (comp-home store))
     (comment comp-debug store {:bottom 0, :max-width "100%", :left 0})
     (comp-msg-list (get-in store [:state :notifications]) :state/remove-notification))))

(def comp-container (create-comp :container render))
