
(ns floating-server.twig.new-list (:require [recollect.bunch :refer [create-twig]]))

(def twig-new-list
  (create-twig
   :new-list
   (fn [messages] (take 20 (sort (fn [a b] (compare (:time a) (:time b))) (vals messages))))))
