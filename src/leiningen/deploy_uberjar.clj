(ns leiningen.deploy-uberjar
  (:require [leiningen.uberjar :as luber]
            [leiningen.pom :as lpom]
            [leiningen.deploy :as ldeploy]
            [clojure.java.io :as io]))

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

(defn get-uberjar-path [project]
  (format "%s/%s" (:target-path project)(:uberjar-name project)))

(defn uberjar? [uberjar]
  (.exists (io/as-file uberjar)))

(defn deploy-uberjar
  "Deploy uberjar to the given repository"
  [project & args]
  (let [repo (first args)
        uberjar (get-uberjar-path project)]
    (verify-repo project repo)
    (if (uberjar? uberjar)
      (ldeploy/deploy project repo (get-group-name project) (:version project) uberjar (lpom/pom project))
      (exit "uberjar does not exist, try lein uberjar first"))))
