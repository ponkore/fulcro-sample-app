(ns fulcro-sample-app.workspaces
  (:require
    [nubank.workspaces.core :as ws]
    [fulcro-sample-app.demo-ws]))

(defonce init (ws/mount))
