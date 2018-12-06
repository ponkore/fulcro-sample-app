(ns fulcro-sample-app.server-components.http-server
  (:require
    [fulcro-sample-app.server-components.config :refer [config]]
    [fulcro-sample-app.server-components.middleware :refer [middleware]]
    [mount.core :refer [defstate]]
    [org.httpkit.server :as http-kit]))

(defstate http-server
  :start (http-kit/run-server middleware (:http-kit config))
  :stop (http-server))
