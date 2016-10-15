(ns courses-clj.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [courses-clj.core-test]
   [courses-clj.common-test]))

(enable-console-print!)

(doo-tests 'courses-clj.core-test
           'courses-clj.common-test)
