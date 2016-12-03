
(ns floating.comp.home
  (:require [respo.alias :refer [create-comp div a]]
            [respo.comp.text :refer [comp-text]]
            [respo.comp.space :refer [comp-space]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [floating.comp.login :refer [comp-login]]
            [floating.comp.draft :refer [comp-draft]]
            [floating.comp.message :refer [comp-message]]
            [floating.comp.msg-reader :refer [comp-msg-reader]]))

(def style-title {:font-size 20, :font-weight 300, :font-family "Josefin Sans"})

(defn on-log-out [e dispatch!] (dispatch! :user/log-out nil))

(def style-empty {:color colors/texture-light})

(defn render-message-list [messages]
  (if (empty? messages)
    (div {:style style-empty, comp-text "List is empty."})
    (div {} (->> messages (map (fn [message] [(:id message) (comp-message message)]))))))

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
           :hot
             (div
              {}
              (div {:style style-title} (comp-text "Hot" nil))
              (render-message-list (:messages store)))
           :new
             (div
              {}
              (div {:style style-title} (comp-text "New" nil))
              (render-message-list (:messages store)))
           :reader (div {} (div {}) (comp-msg-reader (:data store)))
           :add (comp-draft)
           nil))
       (comp-login)))))

(def comp-home (create-comp :home render))
