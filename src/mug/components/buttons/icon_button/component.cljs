(ns mug.components.buttons.icon_button.component)

(defn- button [icon style handle-click]
  [:div {:class (str "icon-button " style)
         :on-click #(handle-click)}
   icon])

(defn play-button [handle-click]
  [button [:i.fa.fa-play] "green" handle-click])

(defn stop-button [handle-click]
  [button [:i.fa.fa-stop] "red" handle-click])
