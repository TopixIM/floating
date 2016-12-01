
(ns floating.comp.home
  (:require [respo.alias :refer [create-comp div a]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [floating.comp.login :refer [comp-login]]
            [floating.comp.draft :refer [comp-draft]]
            [floating.comp.message :refer [comp-message]]))

(defn on-log-out [e dispatch!] (dispatch! :user/log-out nil))

(defn render-message-list [messages]
  (div {} (->> messages (map (fn [message] [(:id message) (comp-message message)])))))

(def style-trigger
  {:color :white,
   :font-size 14,
   :background-color colors/motif-light,
   :cursor :pointer,
   :padding "0 8px"})

(def style-container {:padding "8px 16px"})

(defn render [store]
  (fn [state mutate!]
    (div
     {:style (merge ui/row style-container)}
     (comp-space 8 nil)
     (if (:logged-in? store)
       (let [router (get-in store [:state :router])]
         (case (:name router)
           :profile
             (div
              {:style ui/flex}
              (comp-text (str "Hello! " (get-in store [:user :name])) nil)
              (comp-space 8 nil)
              (a
               {:style style-trigger, :event {:click on-log-out}}
               (comp-text "Log out" nil)))
           :hot (div {} (div {}) (render-message-list (:messages store)))
           :new (div {} (div {}) (render-message-list (:messages store)))
           :add (comp-draft)
           nil))
       (comp-login)))))

(def comp-home (create-comp :home render))
