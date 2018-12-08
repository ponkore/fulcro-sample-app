(ns fulcro-sample-app.ui.counter
  (:require
   [fulcro.server :refer [defmutation]]
   [taoensso.timbre :as log]
   [clojure.java.jdbc :as j]))

(defmutation bump-number [ignored]
  (action [{:keys [datasource] :as env}]
    (j/with-db-transaction [t-conn {:datasource datasource} {:read-only? false}]
      (let [curval (j/query t-conn ["select cnt from count_table"])
            newval (if (and curval (> (count curval) 0))
                     (let [{:keys [cnt]} (first curval)
                           cnt (inc cnt)]
                       (j/execute! t-conn ["update count_table set cnt = ?" cnt])
                       cnt)
                     (let [cnt 1]
                       (j/execute! t-conn ["insert into count_table (cnt) values (?)" cnt])
                       cnt))]
        {:counter/cnt newval}))))
