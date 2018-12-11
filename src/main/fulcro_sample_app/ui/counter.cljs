(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.client.primitives :as prim :refer [defsc]]
   [fulcro.client.mutations :refer [defmutation]]
   [fulcro.client.dom :as dom]
   [sablono.core :refer [html]]))

(defmutation bump-number [ignored]
  (action [{:keys [state] :as param}]
    (swap! state update-in [:counter/counter :counter/cnt] inc))
  #_(remote [env] true))

(defsc Counter
  "simple counter example component"
  [this {:keys [counter/cnt]}]
  {:query         [:counter/cnt]
   :initial-state {:counter/cnt 1}}
  (html
   [:button {:on-click #(prim/transact! this `[(bump-number {})])}
    "You've clicked this button [" cnt "] times."]))

(def ui-counter (prim/factory Counter))
