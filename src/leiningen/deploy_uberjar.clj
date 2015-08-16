(ns leiningen.deploy-uberjar
  (:require [leiningen.uberjar :as luber]
            [leiningen.pom :as lpom]))

(defn exit [message]
  (leiningen.core.main/info message)
  (System/exit 1))

(defn repo-target?
  "verify the given repository is defined in the project"
  [project repo]
  (some #( = repo %) (map #(first %) (:repositories project))))

(defn verify-repo [project repo]
  (when-not (repo-target? project repo)
    (exit (format "Could not find the target repository: %s." repo))))

(defn deploy-uberjar
  "Deploy uberjar to the given repository"
  [project & args]
  (let [repo (first args) ]
    (verify-repo project repo)))
