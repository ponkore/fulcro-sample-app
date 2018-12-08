(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.server :refer [defmutation]]))

(def state (atom {:counter/cnt 0}))

(defmutation bump-number [ignored]
  (action [_]
    (swap! state update :counter/cnt inc)))
