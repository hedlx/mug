(ns mug.media_session)

(defn- sess-apply! [fun]
  (when-let [sess (.-mediaSession js/navigator)]
    (fun sess)))

(defn sess-playing! []
  (sess-apply! #(set! (.-playbackState %) "playing")))

(defn sess-paused! []
  (sess-apply! #(set! (.-playbackState %) "paused")))

(defn- set-session-meta! []
  (sess-apply!
   #(set! (.-metadata %)
          (js/MediaMetadata.
           (clj->js {:title "Symphony"
                     :artist "Me"
                     :album "Generative symphonies"
                     :artwork [{:src "https://upload.wikimedia.org/wikipedia/commons/c/cc/Icon_Pinguin_1_512x512.png"
                                :type "image/png"
                                :sizes "512x512"}]})))))

(defn- set-callbacks! [on-play on-pause]
  (sess-apply!
   (fn [sess]
     (.setActionHandler sess "play" #(do (on-play) (sess-playing!)))
     (.setActionHandler sess "pause" #(do (on-pause) (sess-paused!))))))

(defn setup-media-session! [on-play on-pause]
  (set-session-meta!)
  (set-callbacks! on-play on-pause))