(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.server :refer [defmutation defquery-root defquery-entity]]
   [taoensso.timbre :as log]
   [clojure.java.jdbc :as j]))

(defn get-curval
  [conn]
  (let [curval (j/query conn ["select cnt from count_table"])]
    (if (and curval (> (count curval) 0))
      (let [{:keys [cnt]} (first curval)]
        curval)
      0)))

(defquery-root :counter/counter
  (value [{:keys [parser query datasource] :as env} params]
    (log/info "query :counter/cnt called.")
    (j/with-db-connection [conn {:datasource datasource}]
      {:counter/cnt (get-curval conn)})))

(defmutation bump-number [ignored]
  (action [{:keys [datasource] :as env}]
    (j/with-db-transaction [t-conn {:datasource datasource} {:read-only? false}]
      (j/execute! t-conn ["update count_table set cnt = cnt + 1"])
      (let [curval (get-curval t-conn)]
        {:counter/cnt curval}))))
