(ns user
  (:require [ring.adapter.jetty :refer :all]
            [simone-4.handler :refer :all]
            [clojure.repl :refer :all]))

(def server (atom nil))
(def port 9123)

(defn init! [] (->> (run-jetty #'app {:port port :join? false})
                    (reset! server)))

(defn start! [] (-> @server .start))

(defn stop! [] (.stop @server))

(defn restart! [] (.stop @server) (.start @server))

