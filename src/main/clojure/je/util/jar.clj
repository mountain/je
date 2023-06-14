(ns je.util.jar
  (:import [je.util ResourcesIO]))

(defn cp-resource-dir [resource-dir dest-dir]
  (ResourcesIO/copyResourceDir resource-dir dest-dir))
