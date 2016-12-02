
(ns floating-server.twig.new-list (:require [recollect.bunch :refer [create-twig]]))

(def twig-new-list
  (create-twig
   :new-list
   (fn [messages users]
     (->> (vals messages)
          (sort (fn [a b] (compare (:time b) (:time a))))
          (take 20)
          (map
           (fn [message]
             (let [user (get users (:author-id message))] (assoc message :user user))))))))
