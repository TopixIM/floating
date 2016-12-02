
(ns floating-server.twig.container
  (:require [recollect.bunch :refer [create-twig]]
            [floating-server.twig.user :refer [twig-user]]
            [floating-server.twig.new-list :refer [twig-new-list]]
            [floating-server.twig.hot-list :refer [twig-hot-list]]))

(def twig-container
  (create-twig
   :container
   (fn [db state]
     (let [logged-in? (some? (:user-id state)), router (:router state)]
       {:state state,
        :logged-in? logged-in?,
        :statistics {:count (count (:messages db))},
        :messages (case (:name router)
          :new (twig-new-list (:messages db) (:users db))
          :hot (twig-hot-list (:messages db) (:users db))
          nil),
        :user (if logged-in? (twig-user (get-in db [:users (:user-id state)])) nil)}))))
