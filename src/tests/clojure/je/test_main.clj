(ns je.test_main
    (:require [clojure.test :refer :all])
     (:gen-class :main true))

(defn main-test []
  (testing "main-test"
    (org.junit.runner.JUnitCore/runClasses je.test.AllTests)
    (is (= 1 0))))

(defn -main [& args])
