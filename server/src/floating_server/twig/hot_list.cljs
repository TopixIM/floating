
(ns floating-server.twig.hot-list (:require [recollect.bunch :refer [create-twig]]))

(def twig-hot-list
  (create-twig
   :new-list
   (fn [messages]
     (take 20 (sort (fn [a b] (compare (:score b) (:score a))) (vals messages))))))
