(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.server :refer [defmutation]]
   [taoensso.timbre :as log]))

(def state (atom {:counter/cnt 0}))

(defmutation bump-number [ignored]
  (action [{:keys [datasource] :as env}]
    (log/info "bump-number ds=" datasource)
    ;; TODO: handle with datasource
    (swap! state update :counter/cnt inc)))
