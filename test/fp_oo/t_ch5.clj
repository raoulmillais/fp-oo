(ns fp_oo.t-ch5
    (:use midje.sweet)
    (:use [fp_oo.ch5]))

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
