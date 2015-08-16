(ns leiningen.deploy-uberjar
  (:require [leiningen.uberjar :as luber]
            [leiningen.pom :as lpom]
            [leiningen.deploy :as ldeploy]))

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


(defn get-group-name [project]
  (format "%s/%s" (:group project) (:name project)))

(defn deploy-uberjar
  "Deploy uberjar to the given repository"
  [project & args]
  (let [repo (first args) ]
    (verify-repo project repo)
    (ldeploy/deploy project repo (get-group-name project) (:version project)
                    (luber/uberjar project)
                    (lpom/pom project))))
