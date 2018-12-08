(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.server :refer [defmutation]]))

(defmutation bump-number [ignored]
  (action [{:keys [state]}]
    (swap! state update :counter/cnt inc)))
