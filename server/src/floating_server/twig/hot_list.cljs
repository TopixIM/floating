
(ns floating-server.twig.hot-list (:require [recollect.bunch :refer [create-twig]]))

(def twig-hot-list
  (create-twig
   :new-list
   (fn [messages users]
     (->> (vals messages)
          (sort (fn [a b] (compare (:score b) (:score a))))
          (take 20)
          (map
           (fn [message]
             (let [user (get users (:author-id message))] (assoc message :user user))))))))
