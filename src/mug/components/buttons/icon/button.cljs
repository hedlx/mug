(ns mug.components.buttons.icon.button)

(defn- button [icon style handle-click]
  [:div {:class (str "icon-button " style)
         :on-click #(handle-click)}
   icon])

(defn play-button [handle-click]
  [button [:i.fa.fa-play] "green" handle-click])

(defn stop-button [handle-click]
  [button [:i.fa.fa-stop] "red" handle-click])
