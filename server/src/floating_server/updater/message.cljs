
(ns floating-server.updater.message )

(defn vote [db op-data state-id op-id op-time]
  (update-in db [:messages op-data :score] inc))

(defn add-one [db op-data state-id op-id op-time]
  (let [state (get-in db [:states state-id])]
    (assoc-in
     db
     [:messages op-id]
     {:time op-time, :id op-id, :score 30, :author-id (:user-id state), :text op-data})))
