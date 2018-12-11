(ns fulcro-sample-app.ui.root
  (:require
    [fulcro.client.dom :as dom :refer [div]]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro-sample-app.ui.components :as comp]
    [fulcro-sample-app.ui.counter :as counter]
    [taoensso.timbre :as log]
    [sablono.core :refer [html]]))

(defsc Root [this {:keys [root/message counter/counter]}]
  {:query         [:root/message
                   {:counter/counter (prim/get-query counter/Counter)}]
   :initial-state {:root/message "Hello!"}}
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
      (counter/ui-counter {:counter/cnt 10})]]]))
