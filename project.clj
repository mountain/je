(defproject je "0.0.0"
  :description "FIXME: write description"
  :url "https://github.com/mountain/ada"
  :license {:name "GPL-2.0"
            :url "https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/core.async "1.6.673"]
                 [org.clojure/tools.cli "1.0.214"]
                 [org.clojure/data.json "2.4.0"]
                 [org.apache.commons/commons-io "1.3.2"]
                 [com.google.code.findbugs/jsr305 "3.0.2"]
                 [javax.annotation/javax.annotation-api "1.2"]
                 [org.apache.commons/commons-lang3 "3.12.0"]
                 [junit/junit "4.13" :scope "tests"]
                 [org.mockito/mockito-core "3.12.4" :scope "tests"]
                 [org.hamcrest/hamcrest-core "2.2" :scope "tests"]
                 [org.junit.jupiter/junit-jupiter "5.8.2" :scope "tests"]
                 [com.googlecode.junit-toolbox/junit-toolbox "2.4"]]

  :jvm-opts ["-Xms128m" "-Xmx1g"]
  :javac-options ["--release" "21"]
  :omit-source true

  :source-paths ["src/main/clojure"]
  :java-source-paths ["src/main/java" "src/tests/java/"]
  :resource-paths ["src/main/java" "src/main/resources"]
  :test-paths ["src/tests/clojure/"]

  :aot :all

  :jar-name "je.jar"
  :uberjar-name "jes.jar"

  :main je.main)
