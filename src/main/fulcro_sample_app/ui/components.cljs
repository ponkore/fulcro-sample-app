(ns fulcro-sample-app.ui.components
  (:require
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.client.dom :as dom]
    [sablono.core :refer-macros [html]]))

(defsc PlaceholderImage
  "Generates an SVG image placeholder of the given size and with the given label
  (defaults to showing 'w x h'.

  ```
  (ui-placeholder {:w 50 :h 50 :label \"avatar\"})
  ```
  "
  [this {:keys [w h label]}]
  (let [label (or label (str w "x" h))]
    (html
     [:svg {:width w :height h}
      [:rect {:width w :height h :style {:fill         "rgb(200,200,200)"
                                         :stroke-width 2
                                         :stroke       "black"}}]
      [:text {:text-anchor "middle" :x (/ w 2) :y (/ h 2)} label]])))

(def ui-placeholder (prim/factory PlaceholderImage))
