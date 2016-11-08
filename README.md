# lein-deploy-uberjar

This a simple lein plugin that will take a built uberjar and deploy it to a
defined repository in the project.

[![Clojars Project](https://clojars.org/camechis/deploy-uberjar/latest-version.svg)](https://clojars.org/camechis/deploy-uberjar)

# Example:

Add this to your plugins section in your project.clj

```clojure
[camechis/deploy-uberjar "0.3.0"]
```

The following will deploy the uberjar to the release repository

```
lein deploy-uberjar release
```
