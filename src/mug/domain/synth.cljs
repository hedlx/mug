(ns mug.domain.synth
  (:require [goog.math :as math]))

(defn make-audio-ctx [rate]
  (let [ctx (if js/window.AudioContext.
              (js/window.AudioContext. (clj->js {:sampleRate rate}))
              (js/window.webkitAudioContext. (clj->js {:sampleRate rate})))]
    (.then (-> ctx (.-audioWorklet) (.addModule "worklet/buffered.js")) (fn [] ctx))))

(defn- make-buffer-proc [ctx on-req]
  (let [node (js/AudioWorkletNode. ctx "buffer-processor")]
    (.connect node (.-destination ctx))
    (-> node
        (.-port)
        (-> .-onmessage (set! (fn [e] (on-req e node)))))
    node))

(defn- fnt [x] (with-meta (fn [] (int x)) {:fnt true}))
(defn- make-buffer-js [size gen-fn offset]
  (clj->js
   (->> (range offset (+ offset size))
        (map #(math/clamp
               -1 1
               (try
                 (gen-fn (fnt %))
                 (catch js/Error _ 0)))))))

(defn- send-proc [proc data]
  (-> proc (.-port) (.postMessage data)))

(defn make-on-req [ctx]
  (fn [_ node]
    (let [buffer-size (:buffer-size @ctx)
          offset (:offset @ctx)
          gen-fn (:gen-fn @ctx)
          new-offset (+ buffer-size offset)]
      (swap! ctx assoc :offset new-offset)
      (send-proc node (make-buffer-js buffer-size gen-fn offset)))))

(defn- actual-play [ctx init-offset]
  (let [audio-ctx (:ctx @ctx)
        buffer-size (:buffer-size @ctx)
        gen-fn (:gen-fn @ctx)
        buffer (make-buffer-js buffer-size gen-fn init-offset)
        node (make-buffer-proc audio-ctx (make-on-req ctx))]
    (send-proc node buffer)
    (swap! ctx assoc :offset (+ init-offset buffer-size))
    (swap! ctx assoc :node node)))

(defn play [ctx offset]
  (if-let [audio-ctx (:ctx @ctx)]
    (do (-> (:node @ctx) (.-parameters) (.get "stopped") (.setValueAtTime false (.-currentTime audio-ctx))) (js/Promise.resolve audio-ctx))
    (.then (make-audio-ctx (:rate @ctx)) #(do (swap! ctx assoc :ctx %) (actual-play ctx offset)))))

(defn stop [ctx]
  (when-let [audio-ctx (:ctx @ctx)]
    (when-let [node (:node @ctx)]
      (-> node (.-parameters) (.get "stopped") (.setValueAtTime true (.-currentTime audio-ctx))))))
