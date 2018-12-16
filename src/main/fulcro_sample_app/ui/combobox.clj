(ns fulcro-sample-app.ui.combobox
  (:require
   [fulcro.server :refer [defmutation defquery-root defquery-entity]]
   [taoensso.timbre :as log]
   [clojure.java.jdbc :as j]))

(defquery-root :root/combobox
  (value [{:keys [parser query datasource] :as env} params]
    (j/with-db-connection [conn {:datasource datasource}]
      {:cb/selected-id 1
       :cb/items [{:ci/id 0 :ci/name "あいうえお"}
                  {:ci/id 1 :ci/name "かきくけこ"}
                  {:ci/id 2 :ci/name "さしすせそ"}
                  {:ci/id 3 :ci/name "たちつてと"}]})))
