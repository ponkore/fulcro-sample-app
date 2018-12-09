(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.client.primitives :as prim :refer [defsc]]
   [fulcro.client.mutations :refer [defmutation]]
   [fulcro.client.dom :as dom]
   [sablono.core :refer [html]]))

(defmutation bump-number [ignored]
  (action [{:keys [state]}]
    (.log js/console "bump-number.action counter/cnt=" (:counter/cnt @state))
    (swap! state update :counter/cnt inc))
  (remote [env] true))

(defsc Counter
  "simple counter example component"
  [this {:keys [counter/cnt]}]
  {:query      [:counter/cnt]}
  (html
   [:button {:on-click #(prim/transact! this `[(bump-number {})])}
    "You've clicked this button " (-> cnt first :cnt) " times."]))

(def ui-counter (prim/factory Counter))
