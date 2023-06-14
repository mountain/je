(ns je.main
    (:import [org.gjt.sp.jedit jEdit])
    (:require
        [clojure.string :as string]
        [clojure.tools.cli :as cli]
        [je.util.jar :as jar])
    (:gen-class :main true))

(def cli-options
  ;; An option with a required argument
  [;; A port number (:default is 80)
   ["-p" "--port PORT" "Port number"
    :default 80
    :id :port
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ;; A non-idempotent option (:default is applied first)
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :update-fn inc]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn run-init []
    (let [home (System/getProperty "user.home")
          separator   (System/getProperty "file.separator")
          je-dir (str home separator ".je")]
        (jar/cp-resource-dir "doc" (str je-dir separator "doc") )
        (jar/cp-resource-dir "keymaps" (str je-dir separator "keymaps"))
        (jar/cp-resource-dir "macros" (str je-dir separator "macros"))
        (jar/cp-resource-dir "modes" (str je-dir separator "modes"))
        (jar/cp-resource-dir "properties" (str je-dir separator "properties"))
        (jar/cp-resource-dir "startup" (str je-dir separator "startup"))))

(defn run-editor []
    (jEdit/main (make-array String 0)))


(defn -main [& args]
    (let [opts (cli/parse-opts args cli-options)
          errors (:errors opts)]
        (if (not-empty errors)
            (.println *err* (str "!" (string/join "|" errors)))
            (let [arguments   (:arguments opts)
                  command     (get arguments 0)]
                (cond
                    (.equals command "init") (run-init)
                    (.equals command "editor") (run-editor)
                    :else (println "Unknown mode"))))))
