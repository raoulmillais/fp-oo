(ns fp_oo.t-ch6
    (:use midje.sweet)
    (:use [fp_oo.ch6]))

(fact "make"
      (make Point 1 2) => {:x 1 :y 2 :__class_symbol__ 'Point})

(facts "apply-message-to"
       (fact "calls the instance method on the class with the instance"
             (apply-message-to Point (make Point 1 2) :shift [1 2])
             => {:x 2 :y 4 :__class_symbol__ 'Point})
       (fact "returns the instance value when there is no method"
             (apply-message-to Point (make Point 1 2) :x) => 1))

(fact "class-name instance method"
      (send-to (make Point 1 2) :class-name) => 'Point)

(fact "class instance method"
      (send-to (make Point 1 2) :class) => Point)

(fact "class-from-instance"
      (class-from-instance (make Point 1 1)) => Point)

(fact "lineage"
      (lineage 'Point) => '(Anything Point))

(fact "class-instance-methods"
      (class-instance-methods 'Point) => (:__instance_methods__ Point))

(fact "factorial-trad"
      (factorial-trad 4) => 24)

(fact "factorial-tail"
      (factorial-tail 4) => 24)

(fact "add-tail"
      (add-tail [1 2 3 4] 0) => 10)

(fact "multiply-tail"
      (multiply-tail [3 2 4 2] 1) => 48)

(fact "recursive function"
      (recursive-function
        (fn [val so-far]
          (assoc so-far val 0))
        [:a :b :c]
        {}) => {:a 0 :b 0 :c 0})

(fact "recursive function"
      (recursive-function
        (fn [val so-far]
          (assoc so-far val (count so-far)))
        [:a :b :c]
        {}) => {:a 0 :b 1 :c 2})
