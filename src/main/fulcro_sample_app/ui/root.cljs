(ns fulcro-sample-app.ui.root
  (:require
    [fulcro.client.dom :as dom :refer [div]]
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro-sample-app.ui.components :as comp]
    [fulcro-sample-app.ui.counter :as counter]
    [fulcro-sample-app.ui.combobox2 :as combobox]
    [taoensso.timbre :as log]
    [sablono.core :refer [html]]))

(defsc Root [this {:keys [root/message
                          root/counter
                          root/combobox]}]
  {:query         [:ui/react-key :root/message
                   {:root/counter (prim/get-query counter/Counter)}
                   {:root/combobox (prim/get-query combobox/Combobox)}]
   :initial-state {:root/message "Hello!"
                   :root/counter {:counter/cnt nil}
                   :root/combobox {:cb/selected-id 1
                                   :cb/items [{:ci/id 0 :ci/name "あいうえお"}
                                              {:ci/id 1 :ci/name "かきくけこ"}
                                              {:ci/id 2 :ci/name "さしすせそ"}
                                              {:ci/id 3 :ci/name "たちつてと"}]}}}
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
      (combobox/ui-combobox {:cb/selected-id 0
                             :cb/items [{:ci/id 0 :ci/name "あいうえお"}
                                        {:ci/id 1 :ci/name "かきくけこ"}
                                        {:ci/id 2 :ci/name "さしすせそ"}
                                        {:ci/id 3 :ci/name "たちつてと"}]})]]]))
