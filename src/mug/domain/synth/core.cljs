(ns mug.domain.synth.core)

(defn audio-context []
  (if js/window.AudioContext. ; Some browsers e.g. Safari don't use the unprefixed version yet.
    (js/window.AudioContext.)
    (js/window.webkitAudioContext.)))

(defonce audio-ctx (audio-context))

(defn- fill-buffer [buffer gen-fn offset]
  (let [data (.getChannelData buffer 0)
        length (.-length data)]
    (doseq [i (range length)] (aset data i (-> (gen-fn (int (+ i offset)))
                                               (mod 256)
                                               (- 128)
                                               (/ 128))))
    buffer))

(defn empty-audio-context []
  ;; Creates external audio context that will be used for controlling playback
  (let [rate 8000
        buffer-size (* rate 10)
        buffer (.createBuffer audio-ctx 1 buffer-size rate)
        next-buffer (.createBuffer audio-ctx 1 buffer-size rate)]
    (atom {:gen-fn (fn [x] 0)
           :rate rate
           :buffer buffer
           :buffer-size buffer-size
           :offset 0
           :source nil
           :started-at 0
           :buffer-offset 0
           :gain 1})))

(defn- create-source [buffer onfinish]
  (doto
   (.createBufferSource audio-ctx)
    (-> .-buffer (set! buffer))
    (-> .-onended (set! onfinish))))

(defn play [ctx offset]
  (let [buffer (fill-buffer (:buffer @ctx) (:gen-fn @ctx) offset)
        source (create-source
                buffer
                (fn []
                  (swap! ctx assoc :buffer-offset 0)
                  (play ctx (+ offset (:buffer-size @ctx)))))]
    (.connect source (.-destination audio-ctx))
    (.start source 0  (:buffer-offset @ctx))
    (swap! ctx assoc :started-at (.-currentTime audio-ctx))
    (swap! ctx assoc :offset offset)
    (swap! ctx assoc :source source)))

(defn stop [ctx]
  (when-let [source (:source @ctx)]
    (swap! ctx assoc :buffer-offset (+ (- (.-currentTime audio-ctx) (:started-at @ctx)) (:buffer-offset @ctx)))
    (set! (.-onended source) nil)
    (.stop source)
    (swap! ctx assoc :source nil)))

(defn set-gen-fn [ctx gen-fn]
  (swap! ctx assoc :gen-fn gen-fn)
  (fill-buffer (:buffer @ctx) (:gen-fn @ctx) (:offset @ctx)))
