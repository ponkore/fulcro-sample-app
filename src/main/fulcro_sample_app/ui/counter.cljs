(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.client.primitives :as prim :refer [defsc]]
   [fulcro.client.mutations :refer [defmutation]]
   [fulcro.client.dom :as dom]
   [sablono.core :refer-macros [html]]))

(defmutation bump-number [ignored]
  (action [{:keys [state]}]
    (swap! state update :counter/cnt inc))
  (remote [env] true))

(defsc Counter
  "simple counter example component"
  [this {:keys [counter/cnt]}]
  (html
   [:button {:on-click #(prim/transact! this `[(bump-number {})])}
    "You've clicked this button " cnt " times."]))

(def ui-counter (prim/factory Counter))
