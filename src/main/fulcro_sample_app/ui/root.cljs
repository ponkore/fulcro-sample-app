(ns fulcro-sample-app.ui.root
  (:require
    [fulcro.client.dom :as dom :refer [div]]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro-sample-app.ui.components :as comp]
    [fulcro-sample-app.ui.counter :as counter]
    [fulcro-sample-app.ui.combobox :as combobox]
    [taoensso.timbre :as log]
    [sablono.core :refer [html]]))

(defsc Root [this {:keys [root/message
                          root/counter
                          root/combobox]}]
  {:query         [:ui/react-key :root/message
                   {:root/counter (prim/get-query counter/Counter)}
                   {:root/combobox (prim/get-query combobox/Combobox)}]
   :initial-state (fn [params]
                    {:root/message "Hello!"
                     :root/counter (prim/get-initial-state counter/Counter {})
                     :root/combobox (prim/get-initial-state combobox/Combobox {})})}
  (html
   [:div.ui.segments
    [:div.ui.top.attached.segment
     [:div.content
      "Welcome to Fulcro!!!"]]
    [:div.ui.attached.segment
     [:div.content
      (comp/ui-placeholder {:w 50 :h 50})
      [:div message]
      [:div "Some content here would be nice!!!"]]]
    ;; counter example
    [:div.ui.attached.segment
     [:div.content
      "Counter example" [:span " "]
      (counter/ui-counter counter)]]
    ;; combobox example
    [:div.ui.attached.segment
     [:div.content
      "Combobox example" [:span " "]
      (combobox/ui-combobox combobox)]]]))
