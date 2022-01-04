(ns mug.media_session)

(defn- new-audio []
  (let [audio (js/Audio. "assets/silence.ogg")]
    (set! (.-loop audio) true)
    (set! (.-id audio) "fake-audio")
    audio))

(defonce audio-el (new-audio))
(.appendChild (.-body js/document) audio-el)

(defn- fake-play [] (.play audio-el))
(defn- fake-pause [] (.pause audio-el))

(defn- sess-apply! [fun]
  (when-let [sess (.-mediaSession js/navigator)]
    (fun sess)))

(defn sess-playing! []
  (sess-apply! #(.then (fake-play) (fn [] (set! (.-playbackState %) "playing")))))

(defn sess-paused! []
  (sess-apply! #(do (set! (.-playbackState %) "paused") (fake-pause))))

(defn- set-session-meta! []
  (sess-apply!
   #(set! (.-metadata %)
          (js/MediaMetadata.
           (clj->js {:title "Symphony"
                     :artist "Me"
                     :album "Generative symphonies"
                     :artwork [{:src (str (.-href js/location) "assets/cover.svg")
                                :type "image/svg+xml"}]})))))

(defn set-callbacks! [on-play on-pause]
  (sess-apply!
   (fn [sess]
     (.setActionHandler sess "play" #(do (on-play) (sess-playing!)))
     (.setActionHandler sess "pause" #(do (on-pause) (sess-paused!))))))

(defn setup-media-session! []
  (set-session-meta!))