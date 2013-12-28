(ns fp_oo.t-ch3
    (:use midje.sweet)
    (:use [fp_oo.ch3]))

(fact "add-points"
      (add-points (Point 2 3) (Point 3 2)) => (Point 5 5))

(fact "add"
      (add (Point 1 2) (Point 7 3)) => (Point 8 5))

(fact "make"
      (make Point 1 2) => (Point 1 2)
      (make Triangle (make Point 0 2)
            (make Point 5 2)
            (make Point 5 5))
      => (Triangle (Point 0 2)
                   (Point 5 2)
                   (Point 5 5)))

(fact "equal-triangles?"
      (equal-triangles? right-triangle right-triangle) => true
      (equal-triangles? right-triangle equal-right-triangle) => true
      (equal-triangles? right-triangle different-triangle) => false
      (equal-triangles2? right-triangle equal-right-triangle right-triangle)
      => true)

(fact "valid-triangle?"
      (valid-triangle?  (make Point 0 2) (make Point 5 2) (make Point 5 5)) 
      => true
      (valid-triangle?  (make Point 0 2) (make Point 0 2) (make Point 5 5)) 
      => false
      (valid-triangle?  (make Point 0 2) (make Point 5 2) (make Point 5 2)) 
      => false
      (valid-triangle?  (make Point 0 2) (make Point 5 2) (make Point 0 2)) 
      => false)
