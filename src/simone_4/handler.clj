(ns simone-4.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clojure.java.jdbc :as j]
            [cheshire.core :refer :all]
            

            [simone-4.config :refer [db]]))

(defn assemble-query [tofind-map]
  (reduce (fn [acc-v [k v]]
            (if-not (nil? v)
              (-> acc-v
                  (update 0 #(str % k "=? "))
                  (conj v))
              acc-v))
          [] tofind-map))

(defn query [table tofind-map]
  (let [aq (assemble-query tofind-map)]
    (->> (update aq 0 #(str "SELECT * FROM " table " WHERE " %))
         (j/query db)
         first)))

(s/defschema Disciplina
  {:name s/Str
   :codigo s/Int})

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "Simone-4"
                   :description "Provê uma API REST para o projeto 4 da Simone"}}}}

   (context "/api" [] 
     (GET "/Disciplina" []
       :return {:result Long}
       :query-params [{nome :- String nil}
                      {codigo :- Long nil}]
       :summary "Retorna uma disciplina do banco de dados em JSON"
       (ok {:result (encode (query "Disciplina" {"nome" nome
                                                 "codigo" codigo}))})))))
