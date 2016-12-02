
(ns floating.comp.time
  (:require [respo.alias :refer [create-comp div]]
            [respo.comp.text :refer [comp-text]]
            [respo-ui.style.colors :as colors]))

(def style-time {:color colors/texture-light, :display :inline-block})

(defn render [time]
  (fn [state mutate!]
    (div
     {:style style-time,
      :attrs {:inner-text (let [date (js/Date. time)
                                year (.getFullYear date)
                                month (.getMonth date)
                                date' (.getDate date)]
                (str year "-" month "-" date'))}})))

(def comp-time (create-comp :time render))
