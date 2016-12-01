
(ns floating-server.schema )

(def message {:time nil, :id nil, :score 30, :author-id nil, :text nil})

(def user {:password nil, :name nil, :nickname nil, :id nil, :avatar nil})

(def database {:states {}, :messages {}, :users {}})

(def state {:router nil, :nickname nil, :user-id nil, :notifications [], :id nil})

(def router {:router nil, :name nil, :title nil, :data {}})

(def notification {:id nil, :kind nil, :text nil})
