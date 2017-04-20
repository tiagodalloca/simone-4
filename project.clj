(defproject simone-4 "0.1.0-SNAPSHOT"
  :description "Projeto 4 da Simone"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [metosin/compojure-api "1.1.10"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [net.sourceforge.jtds/jtds "1.3.1"]
                 [cheshire "5.7.1"]]
  :ring {:handler simone-4.handler/app}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.10.0"]]}})

