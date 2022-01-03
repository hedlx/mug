(ns mug.domain.synth)

(defn make-audio-ctx [rate on-ready]
  (let [ctx (if js/window.AudioContext. ; Some browsers e.g. Safari don't use the unprefixed version yet.
              (js/window.AudioContext. (clj->js {:sampleRate rate}))
              (js/window.webkitAudioContext. (clj->js {:sampleRate rate})))]
    (.then (-> ctx (.-audioWorklet) (.addModule "worklet/buffered.js")) #(do (on-ready ctx)))))

(defn- make-buffer-proc [ctx on-req]
  (let [node (js/AudioWorkletNode. ctx "buffer-processor")]
    (.connect node (.-destination ctx))
    (-> node
        (.-port)
        (-> .-onmessage (set! (fn [e] (on-req e node)))))
    node))

(defn- make-buffer-js [size gen-fn offset]
  (clj->js
   (->> (range offset (+ offset size))
        (map #(-> (gen-fn (int %))
                  (mod 256)
                  (- 128)
                  (/ 128))))))

(defn- send-proc [proc data]
  (-> proc (.-port) (.postMessage data)))

(defn empty-audio-context []
  (let [rate 8000
        buffer-size (/ rate 4)]
    (atom {:gen-fn (fn [x] 128)
           :ctx nil
           :rate rate
           :buffer-size buffer-size
           :offset 0
           :node nil
           :started-at 0
           :buffer-offset 0
           :gain 1})))

(defn- actual-play [ctx offset]
    (let [audio-ctx (:ctx @ctx)
          buffer (make-buffer-js (:buffer-size @ctx) (:gen-fn @ctx) offset)
          node (make-buffer-proc audio-ctx (fn [e node]
                                             (swap! ctx assoc :offset (+ (:buffer-size @ctx) (:offset @ctx)))
                                             (send-proc node (make-buffer-js (:buffer-size @ctx) (:gen-fn @ctx) (:offset @ctx)))))]
      (send-proc node buffer)
      (swap! ctx assoc :started-at (.-currentTime audio-ctx))
      (swap! ctx assoc :offset offset)
      (swap! ctx assoc :node node)))

(defn play [ctx offset]
  (if-let [audio-ctx (:ctx @ctx)]
    (-> (:node @ctx) (.-parameters) (.get "stopped") (.setValueAtTime false (.-currentTime audio-ctx)))
    (make-audio-ctx (:rate @ctx) #(do (swap! ctx assoc :ctx %) (actual-play ctx offset)))))

(defn stop [ctx]
  (when-let [audio-ctx (:ctx @ctx)]
    (when-let [node (:node @ctx)]
      (-> node (.-parameters) (.get "stopped") (.setValueAtTime true (.-currentTime audio-ctx))))))

(defn set-gen-fn [ctx gen-fn]
  (swap! ctx assoc :gen-fn gen-fn))
