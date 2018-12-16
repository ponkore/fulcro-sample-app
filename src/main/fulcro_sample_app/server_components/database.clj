(ns fulcro-sample-app.server-components.database
  (:require
   [mount.core :refer [defstate]]
   [hikari-cp.core :refer [make-datasource]]
   [fulcro-sample-app.server-components.config :refer [config]]))

(defstate database
  :start (make-datasource (:database config))
  :stop (.close database))
