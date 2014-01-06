(ns fp_oo.t-ch7
    (:use midje.sweet)
    (:use [fp_oo.ch7]))

(fact "answer-annotations"
      (answer-annotations [{:course-name "zigging" :limit 4,
                            :registered 3}
                           {:course-name "zagging" :limit 1,
                            :registered 1}]
                          ["zagging"])
      => [{:already-in? false, :spaces-left 1
           :registered 3, :limit 4, :course-name "zigging"}
          {:already-in? true, :spaces-left 0,
           :registered 1, :limit 1, :course-name "zagging"}])

(fact "domain-annotations"
      (domain-annotations [{:registered 1, :spaces-left 1},
                           {:registered 0, :spaces-left 1},
                           {:registered 1, :spaces-left 0}])
      => [{:registered 1, :spaces-left 1, :full? false, :empty? false},
          {:registered 0, :spaces-left 1, :full? false, :empty? true},
          {:registered 1, :spaces-left 0, :full? true, :empty? false}])

(fact "separate"
      (separate odd? [1 2 3 4 5 6]) => ['(1 3 5) '(2 4 6)])

(fact "visible-courses"
      (visible-courses [{:already-in? true :unavailable? false},
                        {:already-in? false :unavailable? true}
                        {:already-in? false :unavailable? false}])
      => [{:already-in? true :unavailable? false},
          {:already-in? false :unavailable? false}])

(fact "ex-1"
      (ex-1 [1]) => '(2))

(fact "ex-2"
      (ex-2 [1]) => '(6))

(fact "ex-3"
      (ex-3 3) => (-> 3 (* 2) inc))

