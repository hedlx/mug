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

(defonce callbacks (atom {}))

(defn- set-session-meta! []
  (sess-apply!
   #(do (set! (.-metadata %)
              (js/MediaMetadata.
               (clj->js {:title "Symphony"
                         :artist "Me"
                         :album "Generative symphonies"
                         :artwork [{:src (str (.-href js/location) "assets/cover.svg")
                                    :type "image/svg+xml"}]}))))))

(defn sess-paused! []
  (sess-apply! #(do (set! (.-playbackState %) "paused") (fake-pause))))

(defn sess-playing! []
  (sess-apply! #(.then (fake-play)
                       (fn []
                         (set-session-meta!)
                         (.setActionHandler % "play" (fn [] ((:on-play @callbacks)) (sess-playing!)))
                         (.setActionHandler % "pause" (fn [] ((:on-pause @callbacks)) (sess-paused!)))
                         (set! (.-playbackState %) "playing")))))

(defn- sess-stopped! []
  (sess-apply! #(do (set! (.-playbackState %) "none") (fake-pause))))

(defn set-callbacks! [on-play on-pause]
  (swap! callbacks assoc :on-play on-play)
  (swap! callbacks assoc :on-pause on-pause))

(defn setup-media-session! []
  (set-session-meta!))

(.addEventListener js/window
                   "beforeunload"
                   (fn []
                     (set! (.-src audio-el) "")
                     (sess-apply! #(set! (.-metadata %) nil))
                     (sess-stopped!)))
