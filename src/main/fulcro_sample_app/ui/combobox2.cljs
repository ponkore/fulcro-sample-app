(ns fulcro-sample-app.ui.combobox2
  (:require
   [fulcro.client.primitives :as prim :refer [defsc]]
   [fulcro.client.mutations :refer [defmutation]]
   [fulcro.client.dom :as dom]
   [sablono.core :refer [html]]))

(defmutation combo-selected [{:keys [selected-id]}]
  (action [{:keys [state] :as param}]
    (swap! state assoc-in [:root/combobox :cb/selected-id] selected-id)))

(defsc ComboItem
  "combobox dropdown list item"
  [this {:keys [ci/id ci/name]}]
  {:query [:ci/id :ci/name]
   :ident [:comboitem/by-id :ci/id]
   :initial-state (fn [{:keys [ci/id ci/name]}]
                    {:ci/id id :ci/name name})}
  (html
   [:option {:value id} name]))

(def ui-combo-item (prim/factory ComboItem {:keyfn :ci/id}))

(defsc Combobox
  "simple combobox example component"
  [this {:keys [cb/selected-id cb/items]}]
  {:query         [:cb/selected-id
                   {:cb/items (prim/get-query ComboItem)}]
   :initial-state (fn [{:keys [cb/selected-id cb/items]}]
                    {:cb/selected-id selected-id :cb/items items})}
  (html
   [:select {:value (or selected-id 0) ;; selected-id が undefined になるタイミングが存在してるので予防措置
             :on-change (fn [e]
                          (let [id (-> e .-target .-value)]
                            (prim/transact! this `[(combo-selected {:selected-id ~id})])))}
    (map ui-combo-item items)]))

(def ui-combobox (prim/factory Combobox))
